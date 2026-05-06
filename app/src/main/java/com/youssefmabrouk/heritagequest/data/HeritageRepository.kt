package com.youssefmabrouk.heritagequest.data

import com.youssefmabrouk.heritagequest.R

class HeritageRepository {
    // These are the categories shown on the category screen.
    val categories = listOf(
        QuizCategory(
            id = CategoryId.RomanHeritage,
            title = "Roman Heritage",
            subtitle = "Unplayable",
            questionCount = 0,
            isPlayable = false,
            imageResId = R.drawable.cat_roman_eljem
        ),
        QuizCategory(
            id = CategoryId.IslamicHeritage,
            title = "Islamic Heritage",
            subtitle = "Unplayable",
            questionCount = 0,
            isPlayable = false,
            imageResId = R.drawable.cat_islamic_kairouan
        ),
        QuizCategory(
            id = CategoryId.PunicPreRoman,
            title = "Punic & Pre-Roman",
            subtitle = "Unplayable",
            questionCount = 0,
            isPlayable = false,
            imageResId = R.drawable.cat_punic_carthage
        ),
        QuizCategory(
            id = CategoryId.ModernHeritage,
            title = "Modern Heritage",
            subtitle = "Playable: modern landmarks and civic culture",
            questionCount = 6,
            isPlayable = true,
            imageResId = R.drawable.photo_culture_city
        ),
        QuizCategory(
            id = CategoryId.NaturalMixed,
            title = "Natural & Mixed Sites",
            subtitle = "Playable: parks, reserves, islands, and landscapes",
            questionCount = 6,
            isPlayable = true,
            imageResId = R.drawable.photo_ichkeul
        ),
        QuizCategory(
            id = CategoryId.Cities,
            title = "Cities",
            subtitle = "Unplayable",
            questionCount = 0,
            isPlayable = false,
            imageResId = R.drawable.cat_cities_tunis
        )
    )

    // Only Modern Heritage and Natural & Mixed Sites have playable questions.
    private val questions = listOf(
        createQuestion("modern_easy_bourguiba", CategoryId.ModernHeritage, Difficulty.Easy, "Which monument is shown in this photo?", "Habib Bourguiba Mausoleum", "Hotel du Lac", "Cite de la Culture", "Tunis Clock Tower", "Monastir", "The mausoleum was begun in 1963 and honors Tunisia's first president.", IllustrationKind.Mausoleum, R.drawable.photo_bourguiba),
        createQuestion("modern_easy_culture", CategoryId.ModernHeritage, Difficulty.Easy, "Which Tunis arts complex is shown in this photo?", "Cite de la Culture", "Theatre municipal de Tunis", "National Library of Tunisia", "Hotel du Lac", "Tunis", "Cite de la Culture is a major contemporary cultural complex on Avenue Mohamed V.", IllustrationKind.CultureCity, R.drawable.photo_culture_city),
        createQuestion("modern_easy_rades", CategoryId.ModernHeritage, Difficulty.Easy, "Which stadium is shown in this photo?", "Hammadi Agrebi Stadium", "Stade Chedly Zouiten", "Stade Taieb Mhiri", "El Menzah Sports Palace", "Rades", "This national stadium opened in 2001 and hosted events for the Mediterranean Games.", IllustrationKind.Stadium, R.drawable.photo_rades),
        createQuestion("modern_easy_hotel", CategoryId.ModernHeritage, Difficulty.Easy, "Which modernist Tunis building is shown in this photo?", "Hotel du Lac", "Bardo Museum", "Dar Lasram", "Municipal Theatre", "Tunis", "Hotel du Lac is famous for its inverted-pyramid Brutalist silhouette.", IllustrationKind.BrutalistHotel, R.drawable.photo_hotel_du_lac),
        createQuestion("modern_easy_theatre", CategoryId.ModernHeritage, Difficulty.Easy, "Which performance venue is shown in this photo?", "Theatre municipal de Tunis", "Cite de la Culture", "Carthage Acropolium", "National Library of Tunisia", "Tunis", "The municipal theatre is a landmark on Avenue Habib Bourguiba.", IllustrationKind.Theatre, R.drawable.photo_theatre_tunis),
        createQuestion("modern_easy_clock", CategoryId.ModernHeritage, Difficulty.Easy, "Which landmark on Avenue Habib Bourguiba is shown?", "Tunis Clock Tower", "Bab el Bhar", "Tour de la Nation", "Hotel du Lac", "Tunis", "The clock tower is a recognizable modern landmark in central Tunis.", IllustrationKind.Library, R.drawable.photo_clock_tunis),

        createQuestion("modern_medium_bourguiba", CategoryId.ModernHeritage, Difficulty.Medium, "Which Monastir monument began construction in 1963?", "Habib Bourguiba Mausoleum", "Ribat of Monastir", "Great Mosque of Kairouan", "Dar Hussein", "Monastir", "The mausoleum was begun in 1963 and contains Bourguiba's tomb.", IllustrationKind.Mausoleum, R.drawable.photo_bourguiba),
        createQuestion("modern_medium_culture", CategoryId.ModernHeritage, Difficulty.Medium, "Which cultural complex was inaugurated on 21 March 2018?", "Cite de la Culture", "Hotel du Lac", "Theatre municipal de Tunis", "National Library of Tunisia", "Tunis", "Cite de la Culture became one of Tunisia's main modern arts venues.", IllustrationKind.CultureCity, R.drawable.photo_culture_city),
        createQuestion("modern_medium_rades", CategoryId.ModernHeritage, Difficulty.Medium, "Which Rades venue opened for the 2001 Mediterranean Games?", "Hammadi Agrebi Stadium", "Stade Olympique de Sousse", "Stade Chedly Zouiten", "Stade Mustapha Ben Jannet", "Rades", "The stadium is one of Tunisia's largest sports venues.", IllustrationKind.Stadium, R.drawable.photo_rades),
        createQuestion("modern_medium_hotel", CategoryId.ModernHeritage, Difficulty.Medium, "Which Tunis landmark is linked to architect Raffaele Contigiani?", "Hotel du Lac", "Cite de la Culture", "Tunis Clock Tower", "Theatre municipal de Tunis", "Tunis", "Hotel du Lac is a Tunisian example of 1970s Brutalist architecture.", IllustrationKind.BrutalistHotel, R.drawable.photo_hotel_du_lac),
        createQuestion("modern_medium_theatre", CategoryId.ModernHeritage, Difficulty.Medium, "Which Art Nouveau venue opened in 1902?", "Theatre municipal de Tunis", "National Library of Tunisia", "Bardo Museum", "Cite de la Culture", "Tunis", "The theatre remains a major urban cultural landmark in the capital.", IllustrationKind.Theatre, R.drawable.photo_theatre_tunis),
        createQuestion("modern_medium_clock", CategoryId.ModernHeritage, Difficulty.Medium, "Which central Tunis landmark stands by the avenue named for Habib Bourguiba?", "Tunis Clock Tower", "Tour de la Culture", "Bourguiba Mausoleum", "Kasbah Square", "Tunis", "The clock tower marks a highly visible modern space in central Tunis.", IllustrationKind.Library, R.drawable.photo_clock_tunis),

        createQuestion("modern_hard_bourguiba", CategoryId.ModernHeritage, Difficulty.Hard, "This monument combines a golden dome, two minarets, and the tomb of Tunisia's first president. What is it?", "Habib Bourguiba Mausoleum", "Sidi Mahrez Mausoleum", "Zaouia of Sidi Sahbi", "Ribat of Sousse", "Monastir", "The 1963 mausoleum is tied to post-independence national memory.", IllustrationKind.Mausoleum, R.drawable.photo_bourguiba),
        createQuestion("modern_hard_culture", CategoryId.ModernHeritage, Difficulty.Hard, "This Avenue Mohamed V complex is a recent national arts venue. What is it?", "Cite de la Culture", "Maison de la Culture Ibn Khaldoun", "Theatre municipal de Tunis", "Palais Kheireddine", "Tunis", "Its modern cultural role makes it different from older heritage monuments.", IllustrationKind.CultureCity, R.drawable.photo_culture_city),
        createQuestion("modern_hard_rades", CategoryId.ModernHeritage, Difficulty.Hard, "Identify the national stadium later named after footballer Hammadi Agrebi.", "Hammadi Agrebi Stadium", "Stade El Menzah", "Stade Chedly Zouiten", "Stade Olympique de Sousse", "Rades", "The Rades stadium is closely associated with national football and large events.", IllustrationKind.Stadium, R.drawable.photo_rades),
        createQuestion("modern_hard_hotel", CategoryId.ModernHeritage, Difficulty.Hard, "Identify the closed Tunis hotel whose widening upper floors made it a Brutalist icon.", "Hotel du Lac", "Hotel Africa", "Dar El Jeld", "Hotel Majestic", "Tunis", "Its unusual inverted-pyramid massing made it one of Tunis's best-known modern buildings.", IllustrationKind.BrutalistHotel, R.drawable.photo_hotel_du_lac),
        createQuestion("modern_hard_theatre", CategoryId.ModernHeritage, Difficulty.Hard, "Which protected monument on Avenue Habib Bourguiba represents Art Nouveau in Tunis?", "Theatre municipal de Tunis", "Cathedral of St. Vincent de Paul", "Municipal Casino of Hammam Lif", "National Theatre of Carthage", "Tunis", "The theatre is older than most modern category items but belongs to modern urban heritage.", IllustrationKind.Theatre, R.drawable.photo_theatre_tunis),
        createQuestion("modern_hard_clock", CategoryId.ModernHeritage, Difficulty.Hard, "This bronze-colored tower is a central Tunis urban symbol. What is it?", "Tunis Clock Tower", "Tour de la Nation", "Minaret of Zitouna", "Victory Monument of Bizerte", "Tunis", "The clock tower is a contemporary city marker rather than an archaeological site.", IllustrationKind.Library, R.drawable.photo_clock_tunis),

        createQuestion("nature_easy_ichkeul", CategoryId.NaturalMixed, Difficulty.Easy, "Which wetland national park is represented by this wildlife photo?", "Ichkeul National Park", "Boukornine National Park", "Jebil National Park", "El Feidja National Park", "Bizerte", "Ichkeul is Tunisia's UNESCO natural World Heritage site.", IllustrationKind.Wetland, R.drawable.photo_ichkeul),
        createQuestion("nature_easy_chambi", CategoryId.NaturalMixed, Difficulty.Easy, "Which mountain area is shown in this photo?", "Djebel Chambi", "Jebel Zaghouan", "Jebel Ressas", "Djebel Serj", "Kasserine", "Djebel Chambi contains Tunisia's highest peak.", IllustrationKind.Mountain, R.drawable.photo_chambi),
        createQuestion("nature_easy_bouhedma", CategoryId.NaturalMixed, Difficulty.Easy, "Which national park is represented by this pre-Saharan wildlife photo?", "Djebel Bou-Hedma", "Ichkeul National Park", "Boukornine National Park", "Zembra and Zembretta", "Gafsa and Sidi Bouzid", "Bou-Hedma protects pre-Saharan landscapes and acacia vegetation.", IllustrationKind.Savanna, R.drawable.photo_bouhedma),
        createQuestion("nature_easy_zembra", CategoryId.NaturalMixed, Difficulty.Easy, "Which island and marine reserve is represented by this coastal wildlife photo?", "Zembra and Zembretta", "Kerkennah Islands", "Djerba Island", "Kneiss Islands", "Gulf of Tunis", "Zembra and Zembretta protect Mediterranean island and marine habitats.", IllustrationKind.Island, R.drawable.photo_zembra),
        createQuestion("nature_easy_boukornine", CategoryId.NaturalMixed, Difficulty.Easy, "Which national park near Hammam Lif is represented by this bird photo?", "Boukornine National Park", "Jebil National Park", "Sidi Toui National Park", "Orbata National Park", "Ben Arous", "Boukornine rises above the Gulf of Tunis.", IllustrationKind.Gulf, R.drawable.photo_boukornine),
        createQuestion("nature_easy_tamerza", CategoryId.NaturalMixed, Difficulty.Easy, "Which mountain oasis is shown in this photo?", "Tamerza", "Matmata", "Mateur", "Korba", "Tozeur", "Tamerza is known for mountain-oasis scenery and waterfalls.", IllustrationKind.Oasis, R.drawable.photo_tamerza),

        createQuestion("nature_medium_ichkeul", CategoryId.NaturalMixed, Difficulty.Medium, "Which Tunisian site is famous for wetland habitats used by migratory birds?", "Ichkeul National Park", "Djebel Chambi", "Bou-Hedma National Park", "Boukornine National Park", "Bizerte", "Ichkeul's lake and marshes are key habitats for wintering birds.", IllustrationKind.Wetland, R.drawable.photo_ichkeul),
        createQuestion("nature_medium_chambi", CategoryId.NaturalMixed, Difficulty.Medium, "Which reserve centers on Tunisia's highest peak, around 1,544 meters?", "Djebel Chambi", "Jebel Zaghouan", "Jebel Semmama", "Jebel Boukornine", "Kasserine", "Chambi's elevation makes it a distinctive mountain ecosystem.", IllustrationKind.Mountain, R.drawable.photo_chambi),
        createQuestion("nature_medium_bouhedma", CategoryId.NaturalMixed, Difficulty.Medium, "Which park is associated with Acacia raddiana and pre-Saharan savanna?", "Djebel Bou-Hedma", "El Feidja National Park", "Ichkeul National Park", "Jebil National Park", "Gafsa and Sidi Bouzid", "Bou-Hedma is important for dryland ecosystem protection.", IllustrationKind.Savanna, R.drawable.photo_bouhedma),
        createQuestion("nature_medium_zembra", CategoryId.NaturalMixed, Difficulty.Medium, "Which protected island group lies near Sidi Daoud in the Gulf of Tunis?", "Zembra and Zembretta", "Kerkennah Islands", "Kuriat Islands", "Galite Islands", "Gulf of Tunis", "The reserve protects steep islands and surrounding sea habitats.", IllustrationKind.Island, R.drawable.photo_zembra),
        createQuestion("nature_medium_boukornine", CategoryId.NaturalMixed, Difficulty.Medium, "Which national park near Tunis is associated with mountain slopes and protected wildlife?", "Boukornine National Park", "Djebel Chambi", "Ichkeul National Park", "Sidi Toui National Park", "Ben Arous", "Boukornine is close to Hammam Lif and the Gulf of Tunis.", IllustrationKind.Gulf, R.drawable.photo_boukornine),
        createQuestion("nature_medium_tamerza", CategoryId.NaturalMixed, Difficulty.Medium, "Which southwest Tunisian oasis is known for canyon scenery and waterfalls?", "Tamerza", "Chenini", "Takrouna", "El Jem", "Tozeur", "Tamerza is a mixed natural and cultural oasis landscape.", IllustrationKind.Oasis, R.drawable.photo_tamerza),

        createQuestion("nature_hard_ichkeul", CategoryId.NaturalMixed, Difficulty.Hard, "Identify Tunisia's only natural UNESCO World Heritage site from its wetland wildlife clue.", "Ichkeul National Park", "El Feidja National Park", "Djebel Serj National Park", "Boukornine National Park", "Bizerte", "Ichkeul was inscribed for its lake, marshes, mountain, and bird migration value.", IllustrationKind.Wetland, R.drawable.photo_ichkeul),
        createQuestion("nature_hard_chambi", CategoryId.NaturalMixed, Difficulty.Hard, "This Kasserine mountain reserve is a refuge for Cuvier's gazelle. What is it?", "Djebel Chambi", "Djebel Bou-Hedma", "Djebel Ressas", "Djebel Zaghouan", "Kasserine", "Chambi is a mountain ecosystem as well as Tunisia's highest point.", IllustrationKind.Mountain, R.drawable.photo_chambi),
        createQuestion("nature_hard_bouhedma", CategoryId.NaturalMixed, Difficulty.Hard, "Which park is important for restoring semi-arid ecosystems against desertification?", "Djebel Bou-Hedma", "Jebil National Park", "Boukornine National Park", "Ichkeul National Park", "Gafsa and Sidi Bouzid", "Bou-Hedma protects a rare pre-Saharan savanna environment.", IllustrationKind.Savanna, R.drawable.photo_bouhedma),
        createQuestion("nature_hard_zembra", CategoryId.NaturalMixed, Difficulty.Hard, "Which biosphere reserve protects Mediterranean island and marine habitats off Cap Bon?", "Zembra and Zembretta", "Kneiss Islands", "Kerkennah Islands", "Djerba Island", "Gulf of Tunis", "Zembra and Zembretta form a small but important island reserve.", IllustrationKind.Island, R.drawable.photo_zembra),
        createQuestion("nature_hard_boukornine", CategoryId.NaturalMixed, Difficulty.Hard, "Identify the protected park near Hammam Lif linked to Boukornine's mountain ecosystem.", "Boukornine National Park", "El Feidja National Park", "Jebel Zaghouan Park", "Orbata National Park", "Ben Arous", "Boukornine's mountain and coastal setting make it a mixed nature landmark near Tunis.", IllustrationKind.Gulf, R.drawable.photo_boukornine),
        createQuestion("nature_hard_tamerza", CategoryId.NaturalMixed, Difficulty.Hard, "This largest mountain oasis lies north of the salt lakes near Tozeur. What is it?", "Tamerza", "Douiret", "Matmata", "Mides", "Tozeur", "Tamerza's springs, canyon walls, and waterfalls make it a distinctive oasis landscape.", IllustrationKind.Oasis, R.drawable.photo_tamerza)
    )

    fun questionsFor(categoryId: CategoryId, difficulty: Difficulty = Difficulty.Medium): List<QuizQuestion> {
        return questions.filter { it.categoryId == categoryId && it.difficulty == difficulty }
    }

    fun category(categoryId: CategoryId): QuizCategory {
        return categories.first { it.id == categoryId }
    }

    private fun createQuestion(
        id: String,
        categoryId: CategoryId,
        difficulty: Difficulty,
        prompt: String,
        answer: String,
        optionTwo: String,
        optionThree: String,
        optionFour: String,
        cityOrLocation: String,
        keyFact: String,
        illustrationKind: IllustrationKind,
        imageResId: Int
    ): QuizQuestion {
        return QuizQuestion(
            id = id,
            categoryId = categoryId,
            difficulty = difficulty,
            prompt = prompt,
            answer = answer,
            options = listOf(answer, optionTwo, optionThree, optionFour),
            cityOrLocation = cityOrLocation,
            keyFact = keyFact,
            illustrationKind = illustrationKind,
            imageResId = imageResId
        )
    }
}

