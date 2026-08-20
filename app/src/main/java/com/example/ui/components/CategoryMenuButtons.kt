package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.outlined.Checkroom
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush as GraphicsBrush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.ProductItem
import com.example.ui.theme.PalaceGoldContainer
import com.example.ui.theme.RoyalMaroon
import com.example.ui.theme.RoyalMaroonDark
import com.example.ui.theme.SandalwoodBrown
import com.example.ui.theme.SandalwoodLight
import com.example.ui.theme.SilkGold
import com.example.ui.theme.SilkGoldLight
import com.example.ui.viewmodel.MenuTab

data class MenuButtonItem(
    val tab: MenuTab,
    val icon: ImageVector,
    val badgeLabel: String
)

@Composable
fun MainMenuGrid(
    selectedTab: MenuTab,
    allProducts: List<ProductItem>,
    onTabSelected: (MenuTab) -> Unit,
    modifier: Modifier = Modifier
) {
    val sareeCount = allProducts.count { it.category.equals("Saree", ignoreCase = true) }
    val handicraftCount = allProducts.count { it.category.equals("Handicraft", ignoreCase = true) }
    val sandalCount = allProducts.count { it.category.equals("Sandal Products", ignoreCase = true) }
    val woodCount = allProducts.count { it.category.equals("Wood Articles", ignoreCase = true) }

    val menuItems = listOf(
        MenuButtonItem(MenuTab.SAREE, Icons.Default.AutoAwesome, "$sareeCount Silks"),
        MenuButtonItem(MenuTab.HANDICRAFT, Icons.Default.Brush, "$handicraftCount Crafts"),
        MenuButtonItem(MenuTab.SANDAL_PRODUCTS, Icons.Default.Spa, "$sandalCount Sandal"),
        MenuButtonItem(MenuTab.WOOD_ARTICLES, Icons.Default.Diamond, "$woodCount Items"),
        MenuButtonItem(MenuTab.ABOUT_US, Icons.Default.Storefront, "Heritage"),
        MenuButtonItem(MenuTab.CONTACT, Icons.Default.Call, "Connect")
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Section Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(SilkGold)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "EMPORIUM SECTIONS",
                    style = MaterialTheme.typography.labelLarge,
                    color = SandalwoodBrown,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                )
            }
            Text(
                text = "Select Category",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // 6 Menu Buttons arranged in 3 rows of 2 cards (or responsive grid)
        for (i in menuItems.indices step 2) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val item1 = menuItems[i]
                MenuButtonCard(
                    item = item1,
                    isSelected = selectedTab == item1.tab,
                    onClick = { onTabSelected(item1.tab) },
                    modifier = Modifier.weight(1f)
                )

                if (i + 1 < menuItems.size) {
                    val item2 = menuItems[i + 1]
                    MenuButtonCard(
                        item = item2,
                        isSelected = selectedTab == item2.tab,
                        onClick = { onTabSelected(item2.tab) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun MenuButtonCard(
    item: MenuButtonItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scale by animateFloatAsState(targetValue = if (isSelected) 1.02f else 1.0f, label = "scale")
    val animatedBorderColor by animateColorAsState(
        targetValue = if (isSelected) SilkGold else Color(0xFFE8DFD8),
        label = "border"
    )

    Card(
        modifier = modifier
            .scale(scale)
            .height(96.dp)
            .testTag("menu_btn_${item.tab.name.lowercase()}")
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) RoyalMaroon else Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 6.dp else 2.dp,
            pressedElevation = 8.dp
        ),
        border = BorderStroke(
            width = if (isSelected) 2.dp else 1.dp,
            color = animatedBorderColor
        )
    ) {
        Box(modifier = Modifier.padding(12.dp)) {
            // Subtle badge tag in top-right
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = if (isSelected) SilkGold else SandalwoodLight,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Text(
                    text = item.badgeLabel,
                    style = MaterialTheme.typography.labelSmall,
                    color = if (isSelected) RoyalMaroonDark else SandalwoodBrown,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }

            Column(
                modifier = Modifier.align(Alignment.BottomStart),
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (isSelected) SilkGold.copy(alpha = 0.25f) else SandalwoodLight
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.tab.title,
                        tint = if (isSelected) SilkGoldLight else RoyalMaroon,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = item.tab.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = if (isSelected) Color.White else RoyalMaroonDark,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    maxLines = 1
                )

                Text(
                    text = item.tab.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (isSelected) SilkGoldLight else SandalwoodBrown.copy(alpha = 0.8f),
                    fontSize = 11.sp,
                    maxLines = 1
                )
            }
        }
    }
}
