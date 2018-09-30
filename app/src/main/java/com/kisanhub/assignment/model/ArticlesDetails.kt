package com.kisanhub.assignment.model

data class ArticlesInfo(val count:Int,
                        val total_count:Int,
                        val page_size:Int,
                        val total_pages:Int,
                        val next:String,
                        val previous:String,
                        val current_page:Int,
                        val metadata: Any,
                        val message:String,
                        val data:List<DetailInfo>)




data class AuthorsItem(val fullName: String,
                       val profileSlug: String,
                       val picture: String)

data class DetailInfo(val regionList: List<RegionListItem>?,
                val getArticleTypeDisplay: String,
                val articleTypeFk: String,
                val createdReadable: String,
                val subscriptionPackage: List<SubscriptionPackageItem>?,
                val publishDateReadable: String,
                val description: String,
                val title: String,
                val featuredImage: List<FeaturedImageItem>?,
                val getStatusDisplay: String,
                val videoContent: String,
                val featuredArticle: Boolean,
                val reportSlug:String,
                val publishDate: String,
                val slug: String,
                val contentCode: String,
                val status: Int,
                val authors: List<AuthorsItem>?)

data class FeaturedImageItem(val imageFile: String)

data class RegionListItem(val name: String,
                          val slug: String)

data class SubscriptionPackageItem(val name: String,
                                   val id: Int)