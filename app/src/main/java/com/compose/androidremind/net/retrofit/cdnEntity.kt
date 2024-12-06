package com.compose.androidremind.net.retrofit

data class CType(
    val id: Long,
    val name: String,
    val imgUrl: String? = null,
    val sort: Long,
    val cNavs: List<CNav>
)

data class CNav(
    val id: Long,
    val name: String,
    val sort: Long,
    val createDate: String,
    val cTexts: List<CText>
)

data class CText(
    val id: Long,
    val tit: String,
    val desc: String,

    val imgUrl: String,

    val linkUrl: String,

    val gitHubUrl: String? = null,

    val sort: Long? = null,
    val createDate: String
)

data class cdnEntity(val data: List<CType>) : baseRes()