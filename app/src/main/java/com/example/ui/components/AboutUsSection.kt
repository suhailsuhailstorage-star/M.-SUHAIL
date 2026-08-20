package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.R
import com.example.ui.theme.EmeraldAccent
import com.example.ui.theme.PalaceGoldContainer
import com.example.ui.theme.RoyalMaroon
import com.example.ui.theme.RoyalMaroonDark
import com.example.ui.theme.SandalwoodBrown
import com.example.ui.theme.SandalwoodLight
import com.example.ui.theme.SilkGold
import com.example.ui.theme.SilkGoldLight
import com.example.ui.theme.TextDark

@Composable
fun AboutUsSection(
    onConnectClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Card with Royal Emporium Banner
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("about_us_card"),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            border = BorderStroke(1.dp, SilkGold.copy(alpha = 0.5f))
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(R.drawable.mysore_cauvery_hero_1787209468999)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Mysore Cauvery Emporium",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.8f)
                                    )
                                )
                            )
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = SilkGold
                        ) {
                            Text(
                                text = "HERITAGE SINCE 1916",
                                color = RoyalMaroonDark,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Mysore Cauvery Silks and Handicraft Emporium",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            lineHeight = 24.sp
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Welcome to Mysore Cauvery Silks and Handicraft Emporium — Mysuru's premier destination for certified pure silk sarees, GI-tagged sandalwood treasures, and master handcrafted rosewood and bronze artifacts.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF423733),
                        lineHeight = 22.sp
                    )

                    Text(
                        text = "Nurtured under the legendary royal patronage of the Mysore Wadiyar Kingdom, our emporium brings together over 400 traditional master weavers, wood inlayers, and stone sculptors from across Karnataka to preserve timeless Indian artistry.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF5E4F49),
                        lineHeight = 20.sp
                    )
                }
            }
        }

        // 4 Heritage Pillars
        Text(
            text = "OUR GUARANTEES & HERITAGE PILLARS",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = SandalwoodBrown,
            letterSpacing = 1.1.sp
        )

        HeritagePillarCard(
            title = "100% Pure Mysore Silk with Real Zari",
            description = "Woven on traditional handlooms and powerlooms using certified mulberry silk thread electroplated with 24-carat pure gold and silver. Silk Mark certified on every piece.",
            icon = Icons.Default.WorkspacePremium
        )

        HeritagePillarCard(
            title = "Genuine Mysore Sandalwood (GI Tagged)",
            description = "Procured under government state quotas, our sandalwood oils, billeted logs, and fragrant deities carry official geographical indication certification and rich lasting natural aroma.",
            icon = Icons.Default.Spa
        )

        HeritagePillarCard(
            title = "UNESCO-Recognized Rosewood Inlay",
            description = "Handcrafted by hereditary master inlayers in Mandi Mohalla, Mysore, utilizing seasoned Indian rosewood and natural dyed wood inlays depicting royal Dasara processions.",
            icon = Icons.Default.Diamond
        )

        HeritagePillarCard(
            title = "Channapatna Wooden Lacquerware",
            description = "Eco-friendly, completely non-toxic hand-turned ivory-wood toys and artifacts colored with natural vegetable turmeric, indigo, and kumkum dyes.",
            icon = Icons.Default.AutoAwesome
        )

        // Store Highlights Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = PalaceGoldContainer)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Verified, contentDescription = null, tint = RoyalMaroon)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Visit Our Emporium in Mysore",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = RoyalMaroonDark
                    )
                }

                Text(
                    text = "Located directly opposite the Mysore Palace North Gate on historic Sayyaji Rao Road. We welcome patrons and art connoisseurs 7 days a week with worldwide insured delivery.",
                    style = MaterialTheme.typography.bodySmall,
                    color = SandalwoodBrown
                )

                Spacer(modifier = Modifier.height(4.dp))

                Button(
                    onClick = onConnectClick,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = RoyalMaroon),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.Send, contentDescription = null, tint = Color.White)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Get in Touch / Plan Your Visit", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun HeritagePillarCard(
    title: String,
    description: String,
    icon: ImageVector
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE8DFD8))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(PalaceGoldContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = RoyalMaroon,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF635650),
                    lineHeight = 18.sp
                )
            }
        }
    }
}
