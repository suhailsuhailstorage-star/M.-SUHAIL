package com.example.ui.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.model.ProductItem
import com.example.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.net.URLEncoder

enum class MenuTab(val title: String, val subtitle: String) {
    SAREE("Saree", "Pure Silk & Zari"),
    HANDICRAFT("Handicraft", "Inlay Art & Brass"),
    SANDAL_PRODUCTS("Sandal Products", "Pure Oil & Carvings"),
    WOOD_ARTICLES("Wood Articles", "Rosewood & Toys"),
    ABOUT_US("About Us", "Heritage & Craft"),
    CONTACT("Contact", "Visit & Reach Us")
}

data class ContactInfo(
    val shopName: String = "Mysore Cauvery Silks and Handicraft Emporium",
    val phone: String = "+91 821 244 5566",
    val altPhone: String = "+91 98450 12345",
    val whatsapp: String = "+919845012345",
    val email: String = "contact@mysorecauverysilks.in",
    val address: String = "Sayyaji Rao Road, Opp. Mysore Palace North Gate, Mysuru, Karnataka 570001, India",
    val mapsUrl: String = "https://maps.google.com/?q=Mysore+Palace+Sayyaji+Rao+Road+Mysuru",
    val timings: String = "10:00 AM – 8:30 PM (Open 7 Days a Week)",
    val certification: String = "Govt. Certified Silk Mark • Craft Mark • 100% Pure GI Tagged Sandalwood"
)

class InventoryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProductRepository

    init {
        val database = AppDatabase.getDatabase(application, viewModelScope)
        repository = ProductRepository(database.productDao())
        viewModelScope.launch {
            repository.ensureInitialData()
        }
    }

    val contactInfo = ContactInfo()

    private val _selectedTab = MutableStateFlow(MenuTab.SAREE)
    val selectedTab: StateFlow<MenuTab> = _selectedTab.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedProduct = MutableStateFlow<ProductItem?>(null)
    val selectedProduct: StateFlow<ProductItem?> = _selectedProduct.asStateFlow()

    private val _editingProduct = MutableStateFlow<ProductItem?>(null)
    val editingProduct: StateFlow<ProductItem?> = _editingProduct.asStateFlow()

    private val _isAddDialogOpen = MutableStateFlow(false)
    val isAddDialogOpen: StateFlow<Boolean> = _isAddDialogOpen.asStateFlow()

    private val _inquiryProduct = MutableStateFlow<ProductItem?>(null)
    val inquiryProduct: StateFlow<ProductItem?> = _inquiryProduct.asStateFlow()

    private val _userMessage = MutableStateFlow<String?>(null)
    val userMessage: StateFlow<String?> = _userMessage.asStateFlow()

    // All products from DB
    val allProducts: StateFlow<List<ProductItem>> = repository.allProducts
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Filtered products for current active category and search query
    val filteredProducts: StateFlow<List<ProductItem>> = combine(
        allProducts,
        _selectedTab,
        _searchQuery
    ) { products, tab, query ->
        val categoryFilter = when (tab) {
            MenuTab.SAREE -> "Saree"
            MenuTab.HANDICRAFT -> "Handicraft"
            MenuTab.SANDAL_PRODUCTS -> "Sandal Products"
            MenuTab.WOOD_ARTICLES -> "Wood Articles"
            MenuTab.ABOUT_US, MenuTab.CONTACT -> null
        }

        if (categoryFilter == null) {
            emptyList()
        } else {
            products.filter { item ->
                val matchesCategory = item.category.equals(categoryFilter, ignoreCase = true)
                val matchesQuery = query.isBlank() ||
                        item.name.contains(query, ignoreCase = true) ||
                        item.description.contains(query, ignoreCase = true) ||
                        item.material.contains(query, ignoreCase = true) ||
                        item.craftOrigin.contains(query, ignoreCase = true)
                matchesCategory && matchesQuery
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun selectTab(tab: MenuTab) {
        _selectedTab.value = tab
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun openProductDetails(product: ProductItem) {
        _selectedProduct.value = product
    }

    fun closeProductDetails() {
        _selectedProduct.value = null
    }

    fun openEditProduct(product: ProductItem) {
        _editingProduct.value = product
    }

    fun closeEditProduct() {
        _editingProduct.value = null
    }

    fun openAddProduct() {
        _isAddDialogOpen.value = true
    }

    fun closeAddProduct() {
        _isAddDialogOpen.value = false
    }

    fun openInquiry(product: ProductItem?) {
        _inquiryProduct.value = product
    }

    fun closeInquiry() {
        _inquiryProduct.value = null
    }

    fun clearUserMessage() {
        _userMessage.value = null
    }

    fun saveProduct(product: ProductItem) {
        viewModelScope.launch {
            if (product.id == 0) {
                repository.insertProduct(product.copy(updatedAt = System.currentTimeMillis()))
                _userMessage.value = "New item '${product.name}' added to catalog!"
            } else {
                repository.updateProduct(product.copy(updatedAt = System.currentTimeMillis()))
                _userMessage.value = "Updated '${product.name}' successfully!"
                if (_selectedProduct.value?.id == product.id) {
                    _selectedProduct.value = product
                }
            }
            closeEditProduct()
            closeAddProduct()
        }
    }

    fun deleteProduct(product: ProductItem) {
        viewModelScope.launch {
            repository.deleteProduct(product)
            _userMessage.value = "Deleted '${product.name}' from inventory."
            closeProductDetails()
            closeEditProduct()
        }
    }

    fun resetToDefaultCatalog() {
        viewModelScope.launch {
            repository.resetToInitialCatalog()
            _userMessage.value = "Catalog restored to default Mysore Cauvery collection."
        }
    }

    fun launchWhatsAppInquiry(context: Context, product: ProductItem?, customMessage: String = "") {
        try {
            val phone = contactInfo.whatsapp.replace("+", "").trim()
            val text = if (product != null) {
                if (customMessage.isNotBlank()) {
                    "Hello Mysore Cauvery Emporium, I am inquiring about: *${product.name}* (Price: ₹${product.price.toInt()}). Note: $customMessage"
                } else {
                    "Hello Mysore Cauvery Emporium, I would like to inquire about *${product.name}* (Category: ${product.category}, Price: ₹${product.price.toInt()}). Please share availability and shipment details."
                }
            } else {
                if (customMessage.isNotBlank()) {
                    "Hello Mysore Cauvery Emporium, $customMessage"
                } else {
                    "Hello Mysore Cauvery Silks and Handicraft Emporium, I would like to know more about your collection and store visit."
                }
            }

            val encoded = URLEncoder.encode(text, "UTF-8")
            val uri = Uri.parse("https://api.whatsapp.com/send?phone=$phone&text=$encoded")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            _userMessage.value = "Could not open WhatsApp: ${e.localizedMessage}"
        }
    }

    fun launchPhoneCall(context: Context) {
        try {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${contactInfo.altPhone}")
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            _userMessage.value = "Could not open dialer: ${e.localizedMessage}"
        }
    }

    fun launchEmail(context: Context, product: ProductItem? = null) {
        try {
            val subject = if (product != null) {
                "Catalog Inquiry: ${product.name} (Mysore Cauvery Emporium)"
            } else {
                "Inquiry: Mysore Cauvery Silks and Handicraft Emporium"
            }
            val body = if (product != null) {
                "Hello Mysore Cauvery Team,\n\nI am interested in purchasing:\nItem: ${product.name}\nCategory: ${product.category}\nPrice: ₹${product.price.toInt()}\n\nPlease provide more information on availability, customization, and courier options.\n\nThank you!"
            } else {
                "Hello Mysore Cauvery Team,\n\nI would like to inquire about your silk sarees and handicrafts catalog.\n\nThank you!"
            }

            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${contactInfo.email}")
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, body)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            _userMessage.value = "Could not open email app: ${e.localizedMessage}"
        }
    }

    fun launchMaps(context: Context) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(contactInfo.mapsUrl)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            _userMessage.value = "Could not open Maps: ${e.localizedMessage}"
        }
    }
}
