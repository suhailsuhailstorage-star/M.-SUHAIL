package com.example.data.local

import com.example.data.model.ProductItem

object InitialCatalogData {
    val items = listOf(
        // Category 1: Saree
        ProductItem(
            name = "Royal Mysore Crepe Silk Saree (Pure Gold Zari)",
            category = "Saree",
            description = "Woven from 100% pure Mulberry silk with certified 24-carat pure gold and silver electroplated zari. Features intricate floral buttas and traditional grand pallu in regal crimson red.",
            price = 18500.0,
            imageUrl = "https://images.unsplash.com/photo-1610030469983-98e550d6193c?auto=format&fit=crop&w=800&q=80",
            material = "100% Pure Mulberry Silk & Gold Zari",
            craftOrigin = "Mysore Silk Weaving Factory (KSIC), Karnataka",
            inStock = true,
            featured = true,
            specs = "Length: 6.25m with running blouse piece, 120 GSM Crepe Silk"
        ),
        ProductItem(
            name = "Heritage Mysore Georgette Silk Saree - Peacock Blue",
            category = "Saree",
            description = "Feather-light pure Mysore georgette silk dyed in magnificent royal peacock blue with delicate antique zari stripes and traditional temple border motifs.",
            price = 14200.0,
            imageUrl = "https://images.unsplash.com/photo-1617627143750-d86bc21e42bb?auto=format&fit=crop&w=800&q=80",
            material = "Pure Mysore Georgette Silk",
            craftOrigin = "Sayyaji Rao Weavers Guild, Mysuru",
            inStock = true,
            featured = true,
            specs = "Length: 6.3m, Lightweight drape, Dry clean only"
        ),
        ProductItem(
            name = "Dussehra Special Golden Yellow Kasuti Silk Saree",
            category = "Saree",
            description = "Celebration edition pure silk saree featuring hand-stitched traditional Karnataka Kasuti geometric needlework with chariot and temple gopuram motifs along the pallu.",
            price = 16800.0,
            imageUrl = "https://images.unsplash.com/photo-1609357605129-26f69add5d6e?auto=format&fit=crop&w=800&q=80",
            material = "Handloom Mysore Raw Silk",
            craftOrigin = "Dharwad-Mysore Artisan Heritage Circle",
            inStock = true,
            featured = false,
            specs = "Kasuti hand embroidery: 4 traditional stitches (Gavanti, Murgi, Negi, Menthi)"
        ),
        ProductItem(
            name = "Classic Bridal Maroon Antique Zari Mysore Silk",
            category = "Saree",
            description = "Timeless royal heirloom bridal saree woven with heavy pure gold zari border, rich mango paisley motifs, and shimmering double-warp silk sheen.",
            price = 24500.0,
            imageUrl = "https://images.unsplash.com/photo-1583391733956-3750e0ff4e8b?auto=format&fit=crop&w=800&q=80",
            material = "Heavy Double-Warp Mysore Silk",
            craftOrigin = "Cauvery Silk Emporium Master Looms",
            inStock = true,
            featured = true,
            specs = "Weight: 750g, Silk Mark Certified, Hallmark Zari"
        ),

        // Category 2: Handicraft
        ProductItem(
            name = "Mysore Rosewood Inlay Wall Art - Dasara Procession",
            category = "Handicraft",
            description = "UNESCO-recognized traditional Mysore inlay art depicting the historic Maharaja Ambari procession. Hand-inlaid using various natural seasoned woods and acrylic veneers on seasoned Indian rosewood.",
            price = 8900.0,
            imageUrl = "https://images.unsplash.com/photo-1579783902614-a3fb3927b675?auto=format&fit=crop&w=800&q=80",
            material = "Seasoned Mysore Rosewood & Natural Wood Inlays",
            craftOrigin = "Mandi Mohalla Inlay Artisans, Mysuru",
            inStock = true,
            featured = true,
            specs = "Dimensions: 24 x 14 inches, Ready to hang with brass hooks"
        ),
        ProductItem(
            name = "Bidriware Silver Inlay Floral Vase & Plate Set",
            category = "Handicraft",
            description = "Centuries-old metal craft of Karnataka. Cast from a blackened alloy of zinc and copper, hand-engraved with pure 99.9% fine silver wire inlay in classic poppy floral patterns.",
            price = 6200.0,
            imageUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?auto=format&fit=crop&w=800&q=80",
            material = "Zinc-Copper Alloy with Pure Fine Silver (99.9%) Wire",
            craftOrigin = "Bidar Heritage Craft Cluster, Karnataka",
            inStock = true,
            featured = false,
            specs = "Height: 8 inches (Vase), Diameter: 7 inches (Plate)"
        ),
        ProductItem(
            name = "Traditional Antique Mysore Peacock Brass Hanging Diya",
            category = "Handicraft",
            description = "Heavy solid bell-metal brass deepam lamp crowned with an intricately detailed dancing Mayura (peacock), paired with solid brass link chains for prayer and decor.",
            price = 4500.0,
            imageUrl = "https://images.unsplash.com/photo-1514432324607-a09d9b4aefdd?auto=format&fit=crop&w=800&q=80",
            material = "Virgin Cast Bell Metal Brass",
            craftOrigin = "Cauvery Artisan Brass Foundry, Mysuru",
            inStock = true,
            featured = true,
            specs = "Height: 12 inches (Diya) + 24 inch chain, Weight: 2.4 kg"
        ),
        ProductItem(
            name = "Ganjifa Hand-Painted Royal Mysore Playing Cards Box",
            category = "Handicraft",
            description = "Revival of the royal Moghul and Mysore Dashavatara card game. 120 circular miniature cards individually hand-painted with natural mineral pigments and lacquer finish in a rosewood box.",
            price = 3800.0,
            imageUrl = "https://images.unsplash.com/photo-1544716278-ca5e3f4abd8c?auto=format&fit=crop&w=800&q=80",
            material = "Handmade Stiffened Cloth & Natural Pigments",
            craftOrigin = "Mysore Royal Palace Art Revival Guild",
            inStock = true,
            featured = false,
            specs = "Complete 120 card Dashavatara deck in carved box"
        ),

        // Category 3: Sandal Products
        ProductItem(
            name = "Pure Mysore Sandalwood Oil (Grade-A Essential Extract)",
            category = "Sandal Products",
            description = "100% natural, steam-distilled Santalum Album heartwood oil directly from certified Karnataka state reserves. Rich, soothing, deeply aromatic fragrance with therapeutic qualities.",
            price = 5600.0,
            imageUrl = "https://images.unsplash.com/photo-1608571423902-eed4a5ad8108?auto=format&fit=crop&w=800&q=80",
            material = "100% Pure Santalum Album (Mysore Sandalwood)",
            craftOrigin = "Govt. Sandalwood Oil Factory, Mysuru",
            inStock = true,
            featured = true,
            specs = "Volume: 10ml concentrated oil in crystal amber bottle"
        ),
        ProductItem(
            name = "Hand-Carved Mysore Fragrant Sandalwood Ganesha",
            category = "Sandal Products",
            description = "Masterfully hand-sculpted Lord Ganesha figurine crafted from single-piece mature fragrant white sandalwood. Exudes a gentle natural aroma that lasts for decades.",
            price = 11200.0,
            imageUrl = "https://images.unsplash.com/photo-1567684014761-b65e2e59b9eb?auto=format&fit=crop&w=800&q=80",
            material = "A-Grade Fragrant Mysore Sandalwood",
            craftOrigin = "Cauvery Master Sculptors, Mysuru",
            inStock = true,
            featured = true,
            specs = "Height: 5.5 inches, Weight: 320g, Certified Wood Certificate included"
        ),
        ProductItem(
            name = "Authentic Mysore Sandalwood Soap Royal Gift Set (Pack of 6)",
            category = "Sandal Products",
            description = "The world-famous heritage soap made with pure natural sandalwood oil and skin conditioners. Renowned for its luxurious lather and refreshing natural fragrance.",
            price = 1250.0,
            imageUrl = "https://images.unsplash.com/photo-1607006314144-84c1f9349887?auto=format&fit=crop&w=800&q=80",
            material = "Pure Sandalwood Oil, Natural Vegetable Oils",
            craftOrigin = "Karnataka Soaps and Detergents Ltd, Mysuru",
            inStock = true,
            featured = false,
            specs = "Pack of 6 x 150g bars in regal gold embossed gift box"
        ),
        ProductItem(
            name = "Mysore Royal Sandalwood Incense & Pooja Paste Pack",
            category = "Sandal Products",
            description = "Hand-rolled premium Agarbatti incense sticks paired with pure sandalwood rubbing stone and pooja wood billet for authentic traditional prayer rituals.",
            price = 950.0,
            imageUrl = "https://images.unsplash.com/photo-1506126613408-eca07ce68773?auto=format&fit=crop&w=800&q=80",
            material = "Sandalwood Powder, Natural Tree Gums, Sandal Billet",
            craftOrigin = "Mysuru Sandalwood Heritage Cottage Unit",
            inStock = true,
            featured = false,
            specs = "Includes 50 Premium Incense Sticks + 50g Sandal Billet + Stone Rub"
        ),

        // Category 4: Wood Articles
        ProductItem(
            name = "Mysore Rosewood Carved Royal Elephant with Brass Inlay",
            category = "Wood Articles",
            description = "Hand-carved majestic royal elephant figurine sculpted from seasoned dark Mysore rosewood, embellished with detailed solid brass inlay trunk and floral blanket.",
            price = 4800.0,
            imageUrl = "https://images.unsplash.com/photo-1582562124811-c09040d0a901?auto=format&fit=crop&w=800&q=80",
            material = "Dalbergia Latifolia (Mysore Rosewood) & Solid Brass",
            craftOrigin = "Mysore Royal Woodcraft Guild",
            inStock = true,
            featured = true,
            specs = "Height: 7 inches, Length: 9 inches, Wax polished natural luster"
        ),
        ProductItem(
            name = "Channapatna Handcrafted Lacquer Wooden Toys Set",
            category = "Wood Articles",
            description = "GI-tagged eco-friendly traditional wooden toys handcrafted on wood lathes from soft Wrightia Tinctoria (Aale Mara) wood and coated with safe natural vegetable dyes.",
            price = 1450.0,
            imageUrl = "https://images.unsplash.com/photo-1596461404969-9ae70f2830c1?auto=format&fit=crop&w=800&q=80",
            material = "Wrightia Tinctoria (Ivory Wood) & Non-toxic Vegetable Lacquer",
            craftOrigin = "Channapatna Toy Town, Karnataka",
            inStock = true,
            featured = true,
            specs = "Set of 5 nesting figures, 100% child-safe and non-toxic"
        ),
        ProductItem(
            name = "Teakwood Royal Mysore Jewelry Box with Velvet Lining",
            category = "Wood Articles",
            description = "Intricately hand-carved floral vine motifs on seasoned solid CP Teakwood. Features antique brass latch and multi-compartment royal scarlet velvet interior.",
            price = 3200.0,
            imageUrl = "https://images.unsplash.com/photo-1513519245088-0e12902e5a38?auto=format&fit=crop&w=800&q=80",
            material = "Seasoned Grade-A Teakwood & Velvet Fabric",
            craftOrigin = "Hunsur-Mysore Woodworkers Association",
            inStock = true,
            featured = false,
            specs = "Dimensions: 10 x 6.5 x 4.5 inches, 2 Tier with Ring Slots"
        ),
        ProductItem(
            name = "Mysore Golden Ambari (Howdah) Procession Wooden Showcase",
            category = "Wood Articles",
            description = "Detailed miniature model of the famous 750 kg golden Howdah mounted atop the royal Mysore elephant during the world-renowned Dasara festival.",
            price = 7500.0,
            imageUrl = "https://images.unsplash.com/photo-1544716278-ca5e3f4abd8c?auto=format&fit=crop&w=800&q=80",
            material = "Indian Rosewood with Gold Gilt Embellishments",
            craftOrigin = "Mysore Heritage Master Carvers",
            inStock = true,
            featured = true,
            specs = "Height: 10 inches, Acrylic display casing included"
        )
    )
}
