package com.example.perfecttimeworldclockandalarm.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

//use GoogleFont Provider provider
// Authority – a string value representing the authority of the font provider for the download request
//providerPackage – a string value representing the the package of the font provider for the download request
//certificates – a resource array containing the certificates for the provider to be signed with

//when downloading fonts from Google, remember to lowerCase and add underscore
//TODO - correctly import fonts

//val provider = GoogleFont.Provider(
//    providerAuthority = "come.google.android.gms.fonts",
//    providerPackage = "com.google.android.gms",
//    certificates = R.array.com_google_android_gms_fonts_certs
//)


// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)