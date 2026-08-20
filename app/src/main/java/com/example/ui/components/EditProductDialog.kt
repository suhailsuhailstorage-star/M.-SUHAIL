package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.R
import com.example.data.model.ProductItem
import com.example.ui.theme.EmeraldAccent
import com.example.ui.theme.PalaceGoldContainer
import com.example.ui.theme.RoyalMaroon
import com.example.ui.theme.RoyalMaroonDark
import com.example.ui.theme.SandalwoodBrown
import com.example.ui.theme.SandalwoodLight
import com.example.ui.theme.SilkGold
import com.example.ui.theme.TextDark

// Curated preset sample images of authentic Mysore items
val PRESET_IMAGES = listOf(
    Pair("Mysore Silk Crimson", "https://images.unsplash.com/photo-1610030469983-98e550d6193c?auto=format&fit=crop&w=800&q=80"),
    Pair("Peacock Blue Silk", "https://images.unsplash.com/photo-1617627143750-d86bc21e42bb?auto=format&fit=crop&w=800&q=80"),
    Pair("Golden Raw Silk", "https://images.unsplash.com/photo-1609357605129-26f69add5d6e?auto=format&fit=crop&w=800&q=80"),
    Pair("Rosewood Inlay", "https://images.unsplash.com/photo-1579783902614-a3fb3927b675?auto=format&fit=crop&w=800&q=80"),
    Pair("Bidri Silver Inlay", "https://images.unsplash.com/photo-1534447677768-be436bb09401?auto=format&fit=crop&w=800&q=80"),
    Pair("Brass Peacock Diya", "https://images.unsplash.com/photo-1514432324607-a09d9b4aefdd?auto=format&fit=crop&w=800&q=80"),
    Pair("Sandalwood Oil Extract", "https://images.unsplash.com/photo-1608571423902-eed4a5ad8108?auto=format&fit=crop&w=800&q=80"),
    Pair("Sandalwood Carving", "https://images.unsplash.com/photo-1567684014761-b65e2e59b9eb?auto=format&fit=crop&w=800&q=80"),
    Pair("Mysore Sandal Soap", "https://images.unsplash.com/photo-1607006314144-84c1f9349887?auto=format&fit=crop&w=800&q=80"),
    Pair("Rosewood Elephant", "https://images.unsplash.com/photo-1582562124811-c09040d0a901?auto=format&fit=crop&w=800&q=80"),
    Pair("Channapatna Wooden Toys", "https://images.unsplash.com/photo-1596461404969-9ae70f2830c1?auto=format&fit=crop&w=800&q=80"),
    Pair("Teak Royal Box", "https://images.unsplash.com/photo-1513519245088-0e12902e5a38?auto=format&fit=crop&w=800&q=80")
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun EditProductDialog(
    initialProduct: ProductItem?,
    defaultCategory: String = "Saree",
    onDismiss: () -> Unit,
    onSave: (ProductItem) -> Unit,
    onDelete: ((ProductItem) -> Unit)? = null
) {
    val isNew = initialProduct == null || initialProduct.id == 0

    var name by remember { mutableStateOf(initialProduct?.name ?: "") }
    var category by remember { mutableStateOf(initialProduct?.category ?: defaultCategory) }
    var description by remember { mutableStateOf(initialProduct?.description ?: "") }
    var priceText by remember { mutableStateOf(if (initialProduct != null && initialProduct.price > 0) initialProduct.price.toInt().toString() else "") }
    var imageUrl by remember { mutableStateOf(initialProduct?.imageUrl ?: PRESET_IMAGES.first().second) }
    var material by remember { mutableStateOf(initialProduct?.material ?: "Pure Mulberry Silk") }
    var craftOrigin by remember { mutableStateOf(initialProduct?.craftOrigin ?: "Mysuru, Karnataka") }
    var inStock by remember { mutableStateOf(initialProduct?.inStock ?: true) }
    var specs by remember { mutableStateOf(initialProduct?.specs ?: "") }

    var categoryExpanded by remember { mutableStateOf(false) }
    val categories = listOf("Saree", "Handicraft", "Sandal Products", "Wood Articles")

    var nameError by remember { mutableStateOf(false) }
    var priceError by remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.94f)
                .padding(vertical = 24.dp)
                .testTag("edit_inventory_dialog"),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                // Dialog Header
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(RoyalMaroon)
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                ) {
                    Column(modifier = Modifier.align(Alignment.CenterStart)) {
                        Text(
                            text = if (isNew) "Add New Catalog Item" else "Edit Inventory Item",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Mysore Cauvery Silks & Handicrafts",
                            style = MaterialTheme.typography.bodySmall,
                            color = SilkGold
                        )
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.White
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Image Preview & Presets
                    Text(
                        text = "1. INVENTORY IMAGE",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = SandalwoodBrown
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(SandalwoodLight)
                            .border(1.dp, SilkGold, RoundedCornerShape(12.dp))
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(imageUrl.ifBlank { R.drawable.mysore_cauvery_hero_1787209468999 })
                                .crossfade(true)
                                .error(R.drawable.mysore_cauvery_hero_1787209468999)
                                .build(),
                            contentDescription = "Preview",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Surface(
                            shape = RoundedCornerShape(topStart = 8.dp),
                            color = Color.Black.copy(alpha = 0.6f),
                            modifier = Modifier.align(Alignment.BottomEnd)
                        ) {
                            Text(
                                text = "Live Preview",
                                color = Color.White,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    // Custom URL Input
                    OutlinedTextField(
                        value = imageUrl,
                        onValueChange = { imageUrl = it },
                        label = { Text("Image URL") },
                        leadingIcon = {
                            Icon(Icons.Default.Image, contentDescription = null, tint = RoyalMaroon)
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_image_url"),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = RoyalMaroon,
                            focusedLabelColor = RoyalMaroon
                        )
                    )

                    // Quick Presets chips
                    Column {
                        Text(
                            text = "Or choose authentic photo preset:",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            PRESET_IMAGES.forEach { (presetName, url) ->
                                FilterChip(
                                    selected = imageUrl == url,
                                    onClick = { imageUrl = url },
                                    label = { Text(presetName, fontSize = 11.sp) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = PalaceGoldContainer,
                                        selectedLabelColor = SandalwoodBrown
                                    )
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Product Details Section
                    Text(
                        text = "2. ITEM DETAILS",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = SandalwoodBrown
                    )

                    // Name
                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                            if (it.isNotBlank()) nameError = false
                        },
                        label = { Text("Product Name *") },
                        leadingIcon = {
                            Icon(Icons.Default.Title, contentDescription = null, tint = RoyalMaroon)
                        },
                        isError = nameError,
                        supportingText = {
                            if (nameError) Text("Product name is required", color = MaterialTheme.colorScheme.error)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_product_name"),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = RoyalMaroon,
                            focusedLabelColor = RoyalMaroon
                        )
                    )

                    // Category Dropdown
                    ExposedDropdownMenuBox(
                        expanded = categoryExpanded,
                        onExpandedChange = { categoryExpanded = !categoryExpanded }
                    ) {
                        OutlinedTextField(
                            value = category,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Category") },
                            leadingIcon = {
                                Icon(Icons.Default.Category, contentDescription = null, tint = RoyalMaroon)
                            },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                                .testTag("select_category"),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = RoyalMaroon,
                                focusedLabelColor = RoyalMaroon
                            )
                        )

                        ExposedDropdownMenu(
                            expanded = categoryExpanded,
                            onDismissRequest = { categoryExpanded = false }
                        ) {
                            categories.forEach { cat ->
                                DropdownMenuItem(
                                    text = { Text(cat) },
                                    onClick = {
                                        category = cat
                                        categoryExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    // Price & Stock status Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = priceText,
                            onValueChange = {
                                priceText = it
                                if (it.isNotBlank()) priceError = false
                            },
                            label = { Text("Price (INR ₹) *") },
                            leadingIcon = {
                                Icon(Icons.Default.Payments, contentDescription = null, tint = RoyalMaroon)
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            isError = priceError,
                            modifier = Modifier
                                .weight(1f)
                                .testTag("input_product_price"),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = RoyalMaroon,
                                focusedLabelColor = RoyalMaroon
                            )
                        )

                        // In Stock Toggle
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Text(
                                text = if (inStock) "In Stock" else "Pre-Order",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = if (inStock) EmeraldAccent else Color.Gray
                            )
                            Switch(
                                checked = inStock,
                                onCheckedChange = { inStock = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = EmeraldAccent
                                )
                            )
                        }
                    }

                    // Description
                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Description & Craft Story") },
                        leadingIcon = {
                            Icon(Icons.Default.Description, contentDescription = null, tint = RoyalMaroon)
                        },
                        minLines = 3,
                        maxLines = 5,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_product_description"),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = RoyalMaroon,
                            focusedLabelColor = RoyalMaroon
                        )
                    )

                    // Material & Origin
                    OutlinedTextField(
                        value = material,
                        onValueChange = { material = it },
                        label = { Text("Material / Purity Certification") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = RoyalMaroon,
                            focusedLabelColor = RoyalMaroon
                        )
                    )

                    OutlinedTextField(
                        value = craftOrigin,
                        onValueChange = { craftOrigin = it },
                        label = { Text("Craft Origin / Weavers Guild") },
                        leadingIcon = {
                            Icon(Icons.Default.LocationOn, contentDescription = null, tint = SilkGold)
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = RoyalMaroon,
                            focusedLabelColor = RoyalMaroon
                        )
                    )

                    OutlinedTextField(
                        value = specs,
                        onValueChange = { specs = it },
                        label = { Text("Specifications / Dimensions / Weight") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = RoyalMaroon,
                            focusedLabelColor = RoyalMaroon
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Actions: Delete / Save
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (!isNew && onDelete != null && initialProduct != null) {
                            OutlinedButton(
                                onClick = { onDelete(initialProduct) },
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = MaterialTheme.colorScheme.error
                                ),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.testTag("delete_product_button")
                            ) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete")
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Delete")
                            }
                        }

                        Button(
                            onClick = {
                                if (name.isBlank()) {
                                    nameError = true
                                    return@Button
                                }
                                val parsedPrice = priceText.toDoubleOrNull() ?: 0.0
                                val updatedItem = (initialProduct ?: ProductItem(
                                    name = name.trim(),
                                    category = category,
                                    description = description.trim(),
                                    price = parsedPrice,
                                    imageUrl = imageUrl.trim(),
                                    material = material.trim(),
                                    craftOrigin = craftOrigin.trim(),
                                    inStock = inStock,
                                    specs = specs.trim()
                                )).copy(
                                    name = name.trim(),
                                    category = category,
                                    description = description.trim(),
                                    price = parsedPrice,
                                    imageUrl = imageUrl.trim(),
                                    material = material.trim(),
                                    craftOrigin = craftOrigin.trim(),
                                    inStock = inStock,
                                    specs = specs.trim()
                                )
                                onSave(updatedItem)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = RoyalMaroon
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("save_product_button")
                        ) {
                            Icon(Icons.Default.Save, contentDescription = null, tint = Color.White)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (isNew) "Add to Catalog" else "Save Changes",
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}
