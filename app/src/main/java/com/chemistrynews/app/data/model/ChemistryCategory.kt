package com.chemistrynews.app.data.model

enum class ChemistryCategory(val displayName: String, val keywords: List<String>) {
    ORGANIC_CHEMISTRY(
        "Chimica Organica",
        listOf("organic chemistry", "chimica organica", "synthesis", "sintesi organica", "reaction mechanism")
    ),
    INORGANIC_CHEMISTRY(
        "Chimica Inorganica",
        listOf("inorganic chemistry", "chimica inorganica", "coordination", "metal", "metallurgy")
    ),
    ANALYTICAL_CHEMISTRY(
        "Chimica Analitica",
        listOf("analytical chemistry", "chimica analitica", "spectroscopy", "chromatography", "mass spectrometry")
    ),
    PHYSICAL_CHEMISTRY(
        "Chimica Fisica",
        listOf("physical chemistry", "chimica fisica", "thermodynamics", "quantum chemistry", "kinetics")
    ),
    BIOCHEMISTRY(
        "Biochimica",
        listOf("biochemistry", "biochimica", "protein", "enzyme", "dna", "molecular biology")
    ),
    MATERIALS_SCIENCE(
        "Scienza dei Materiali",
        listOf("materials science", "materiali", "polymer", "nanotechnology", "nanomaterial", "composite")
    ),
    PHARMACEUTICALS(
        "Farmaceutica",
        listOf("pharmaceutical", "farmaceutica", "drug", "medicine", "medicinal chemistry", "pharmacology")
    ),
    ENVIRONMENTAL_CHEMISTRY(
        "Chimica Ambientale",
        listOf("environmental chemistry", "chimica ambientale", "pollution", "green chemistry", "sustainability")
    );

    companion object {
        fun fromDisplayName(name: String): ChemistryCategory? {
            return values().find { it.displayName == name }
        }
    }
}
