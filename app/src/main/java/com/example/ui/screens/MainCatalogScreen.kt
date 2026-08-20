package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.R
import com.example.data.model.ProductItem
import com.example.ui.components.AboutUsSection
import com.example.ui.components.ContactSection
import com.example.ui.components.EditProductDialog
import com.example.ui.components.InquiryDialog
import com.example.ui.components.MainMenuGrid
import com.example.ui.components.ProductCard
import com.example.ui.components.ProductDetailDialog
import com.example.ui.theme.EmeraldAccent
import com.example.ui.theme.PalaceGoldContainer
import com.example.ui.theme.RoyalMaroon
import com.example.ui.theme.RoyalMaroonDark
import com.example.ui.theme.SandalwoodBrown
import com.example.ui.theme.SandalwoodLight
import com.example.ui.theme.SilkGold
import com.example.ui.theme.SilkGoldLight
import com.example.ui.theme.TextDark
import com.example.ui.viewmodel.InventoryViewModel
import com.example.ui.viewmodel.MenuTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCatalogScreen(
    viewModel: InventoryViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    val selectedTab by viewModel.selectedTab.collectAsStateWithLifecycle()
    val allProducts by viewModel.allProducts.collectAsStateWithLifecycle()
    val filteredProducts by viewModel.filteredProducts.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val selectedProduct by viewModel.selectedProduct.collectAsStateWithLifecycle()
    val editingProduct by viewModel.editingProduct.collectAsStateWithLifecycle()
    val isAddDialogOpen by viewModel.isAddDialogOpen.collectAsStateWithLifecycle()
    val inquiryProduct by viewModel.inquiryProduct.collectAsStateWithLifecycle()
    val userMessage by viewModel.userMessage.collectAsStateWithLifecycle()

    var showMenuDropdown by remember { mutableStateOf(false) }

    LaunchedEffect(userMessage) {
        userMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg, duration = SnackbarDuration.Short)
            viewModel.clearUserMessage()
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Mysore Cauvery",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "Silks & Handicraft Emporium",
                            style = MaterialTheme.typography.labelSmall,
                            color = SilkGoldLight,
                            fontSize = 11.sp,
                            letterSpacing = 0.8.sp
                        )
                    }
                },
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(SilkGold)
                            .padding(2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(R.drawable.mysore_cauvery_icon_1787209451976)
                                .build(),
                            contentDescription = "Shop Icon",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                        )
                    }
                },
                actions = {
                    // Quick WhatsApp Connect action
                    IconButton(
                        onClick = { viewModel.launchWhatsAppInquiry(context, null) },
                        modifier = Modifier.testTag("top_action_whatsapp")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "WhatsApp",
                            tint = SilkGoldLight
                        )
                    }

                    // Add product quick action
                    IconButton(
                        onClick = { viewModel.openAddProduct() },
                        modifier = Modifier.testTag("top_action_add")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Item",
                            tint = Color.White
                        )
                    }

                    // Overflow Menu
                    Box {
                        IconButton(onClick = { showMenuDropdown = true }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More Options",
                                tint = Color.White
                            )
                        }

                        DropdownMenu(
                            expanded = showMenuDropdown,
                            onDismissRequest = { showMenuDropdown = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Reset to Original Catalog") },
                                leadingIcon = {
                                    Icon(Icons.Default.Refresh, contentDescription = null, tint = RoyalMaroon)
                                },
                                onClick = {
                                    showMenuDropdown = false
                                    viewModel.resetToDefaultCatalog()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("About Emporium") },
                                onClick = {
                                    showMenuDropdown = false
                                    viewModel.selectTab(MenuTab.ABOUT_US)
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Contact & Directions") },
                                onClick = {
                                    showMenuDropdown = false
                                    viewModel.selectTab(MenuTab.CONTACT)
                                }
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = RoyalMaroon
                )
            )
        },
        floatingActionButton = {
            if (selectedTab != MenuTab.ABOUT_US && selectedTab != MenuTab.CONTACT) {
                ExtendedFloatingActionButton(
                    onClick = { viewModel.openAddProduct() },
                    icon = { Icon(Icons.Default.Add, contentDescription = null) },
                    text = { Text("Add ${selectedTab.title} Item", fontWeight = FontWeight.Bold) },
                    containerColor = RoyalMaroon,
                    contentColor = Color.White,
                    modifier = Modifier.testTag("fab_add_product")
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFFAF7F2)),
            contentPadding = PaddingValues(bottom = 90.dp)
        ) {
            // 1. Hero Showcase Banner
            item(key = "hero_banner") {
                HeroShowcaseBanner(
                    onWhatsAppClick = { viewModel.launchWhatsAppInquiry(context, null) },
                    onCallClick = { viewModel.launchPhoneCall(context) },
                    onVisitClick = { viewModel.selectTab(MenuTab.CONTACT) }
                )
            }

            // 2. The 6 Main Menu Buttons (As requested)
            item(key = "main_menu_6_buttons") {
                MainMenuGrid(
                    selectedTab = selectedTab,
                    allProducts = allProducts,
                    onTabSelected = { tab ->
                        viewModel.selectTab(tab)
                    }
                )
            }

            // 3. Active View Section Header & Content
            when (selectedTab) {
                MenuTab.ABOUT_US -> {
                    item(key = "about_us_section") {
                        AboutUsSection(
                            onConnectClick = { viewModel.selectTab(MenuTab.CONTACT) }
                        )
                    }
                }

                MenuTab.CONTACT -> {
                    item(key = "contact_section") {
                        ContactSection(
                            contactInfo = viewModel.contactInfo,
                            onWhatsAppClick = { customNote ->
                                viewModel.launchWhatsAppInquiry(context, null, customNote)
                            },
                            onCallClick = { viewModel.launchPhoneCall(context) },
                            onEmailClick = { viewModel.launchEmail(context) },
                            onMapsClick = { viewModel.launchMaps(context) }
                        )
                    }
                }

                else -> {
                    // Catalog Category (Saree, Handicraft, Sandal Products, Wood Articles)
                    item(key = "catalog_header") {
                        CategoryHeaderWithSearch(
                            categoryName = selectedTab.title,
                            categorySubtitle = selectedTab.subtitle,
                            itemCount = filteredProducts.size,
                            searchQuery = searchQuery,
                            onSearchQueryChange = { viewModel.updateSearchQuery(it) },
                            onAddNewItem = { viewModel.openAddProduct() }
                        )
                    }

                    if (filteredProducts.isEmpty()) {
                        item(key = "empty_state") {
                            EmptyCatalogState(
                                categoryName = selectedTab.title,
                                isSearching = searchQuery.isNotBlank(),
                                onAddItem = { viewModel.openAddProduct() },
                                onReset = { viewModel.resetToDefaultCatalog() }
                            )
                        }
                    } else {
                        items(
                            items = filteredProducts,
                            key = { it.id }
                        ) { product ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 6.dp)
                            ) {
                                ProductCard(
                                    product = product,
                                    onClick = { viewModel.openProductDetails(product) },
                                    onEditClick = { viewModel.openEditProduct(product) },
                                    onInquireClick = { viewModel.openInquiry(product) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // Modal Dialogs
    selectedProduct?.let { product ->
        ProductDetailDialog(
            product = product,
            onDismiss = { viewModel.closeProductDetails() },
            onEditClick = {
                viewModel.closeProductDetails()
                viewModel.openEditProduct(it)
            },
            onWhatsAppInquiry = {
                viewModel.launchWhatsAppInquiry(context, it)
            },
            onCallStore = { viewModel.launchPhoneCall(context) },
            onEmailInquiry = { viewModel.launchEmail(context, it) }
        )
    }

    editingProduct?.let { product ->
        EditProductDialog(
            initialProduct = product,
            defaultCategory = selectedTab.title,
            onDismiss = { viewModel.closeEditProduct() },
            onSave = { updatedProduct ->
                viewModel.saveProduct(updatedProduct)
            },
            onDelete = { toDelete ->
                viewModel.deleteProduct(toDelete)
            }
        )
    }

    if (isAddDialogOpen) {
        val defaultCategory = when (selectedTab) {
            MenuTab.SAREE -> "Saree"
            MenuTab.HANDICRAFT -> "Handicraft"
            MenuTab.SANDAL_PRODUCTS -> "Sandal Products"
            MenuTab.WOOD_ARTICLES -> "Wood Articles"
            else -> "Saree"
        }
        EditProductDialog(
            initialProduct = null,
            defaultCategory = defaultCategory,
            onDismiss = { viewModel.closeAddProduct() },
            onSave = { newProduct ->
                viewModel.saveProduct(newProduct)
            }
        )
    }

    inquiryProduct?.let { prod ->
        InquiryDialog(
            product = prod,
            onDismiss = { viewModel.closeInquiry() },
            onSendWhatsApp = { note ->
                viewModel.closeInquiry()
                viewModel.launchWhatsAppInquiry(context, prod, note)
            },
            onSendEmail = {
                viewModel.closeInquiry()
                viewModel.launchEmail(context, prod)
            }
        )
    }
}

@Composable
private fun HeroShowcaseBanner(
    onWhatsAppClick: () -> Unit,
    onCallClick: () -> Unit,
    onVisitClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag("hero_showcase_banner"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = BorderStroke(1.dp, Color(0xFFEADBCE))
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(R.drawable.mysore_cauvery_hero_1787209468999)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Mysore Cauvery Emporium Banner",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.3f),
                                    Color.Black.copy(alpha = 0.75f)
                                )
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(14.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = SilkGold
                        ) {
                            Text(
                                text = "ESTD. 1916",
                                color = RoyalMaroonDark,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Royal Heritage Collection",
                            color = SilkGoldLight,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Mysore Cauvery Silks and Handicraft Emporium",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        lineHeight = 20.sp
                    )
                }
            }

            // Quick Connect Action Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PalaceGoldContainer)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Direct Store Contact:",
                    style = MaterialTheme.typography.labelSmall,
                    color = SandalwoodBrown,
                    fontWeight = FontWeight.Bold
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = EmeraldAccent,
                        modifier = Modifier.clickable(onClick = onWhatsAppClick)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Send, contentDescription = null, tint = Color.White, modifier = Modifier.size(12.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("WhatsApp", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = RoyalMaroon,
                        modifier = Modifier.clickable(onClick = onCallClick)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Call, contentDescription = null, tint = Color.White, modifier = Modifier.size(12.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Call Us", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CategoryHeaderWithSearch(
    categoryName: String,
    categorySubtitle: String,
    itemCount: Int,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onAddNewItem: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "$categoryName Collection",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = RoyalMaroonDark
                )
                Text(
                    text = "$categorySubtitle • $itemCount Authentic Products",
                    style = MaterialTheme.typography.bodySmall,
                    color = SandalwoodBrown
                )
            }

            OutlinedButton(
                onClick = onAddNewItem,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = RoyalMaroon),
                border = BorderStroke(1.dp, RoyalMaroon)
            ) {
                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Add Item", fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            placeholder = { Text("Search $categoryName by name, material, or craft...", fontSize = 13.sp) },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Search", tint = RoyalMaroon)
            },
            trailingIcon = {
                if (searchQuery.isNotBlank()) {
                    IconButton(onClick = { onSearchQueryChange("") }) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear", tint = Color.Gray)
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("catalog_search_bar"),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = RoyalMaroon,
                unfocusedBorderColor = Color(0xFFE2D7CE)
            )
        )
    }
}

@Composable
private fun EmptyCatalogState(
    categoryName: String,
    isSearching: Boolean,
    onAddItem: () -> Unit,
    onReset: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE6DCD2))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(PalaceGoldContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Inventory2,
                    contentDescription = null,
                    tint = RoyalMaroon,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = if (isSearching) "No matching products found" else "No $categoryName items in inventory",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = TextDark,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = if (isSearching) "Try searching for a different keyword or clear the search filter." else "You can add a new product or restore the curated Mysore Cauvery catalog.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedButton(
                    onClick = onReset,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = SandalwoodBrown)
                ) {
                    Text("Restore Catalog")
                }

                Button(
                    onClick = onAddItem,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = RoyalMaroon)
                ) {
                    Text("Add Item", color = Color.White)
                }
            }
        }
    }
}
