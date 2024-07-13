package com.alura.literalura.service;

public class LanguageConversion {

    public static String convertLanguage(String language) {
        switch (language) {
            case "portugues":
                return "pt";
            case "ingles":
                return "en";
            case "espanhol":
                return "es";
            case "frances":
                return "fr";
            case "italiano":
                return "it";
            case "alemao":
                return "de";
            case "chines":
                return "zh";
            case "japones":
                return "ja";
            case "coreano":
                return "ko";
            case "russo":
                return "ru";
            case "arabe":
                return "ar";
            case "hebraico":
                return "he";
            case "grego":
                return "el";
            case "turco":
                return "tr";
            case "polones":
                return "pl";
            case "sueco":
                return "sv";
            case "noruegues":
                return "no";
            case "dinamarques":
                return "da";
            case "finlandes":
                return "fi";
            case "holandes":
                return "nl";
            case "islandes":
                return "is";
            case "hindi":
                return "hi";
            case "bengali":
                return "bn";
            case "tamil":
                return "ta";
            case "telugo":
                return "te";
            case "marati":
                return "mr";
            case "urdu":
                return "ur";
            case "persa":
                return "fa";
            case "indonesio":
                return "id";
            case "malasio":
                return "ms";
            case "filipino":
                return "tl";
            case "vietnamita":
                return "vi";
            case "tailandes":
                return "th";
            case "malaio":
                return "ms";
            case "swahili":
                return "sw";
            case "afrikaans":
                return "af";
            case "zulu":
                return "zu";
            case "xhosa":
                return "xh";
            case "suaíli":
                return "sw";
            case "hauçá":
                return "ha";
            case "yoruba":
                return "yo";
            case "igbo":
                return "ig";
            case "somalí":
                return "so";
        }

        return language;
    }
}
