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
                        val data:List<ArticlesDetails>)

data class ArticlesDetails(val region_list: List<RegionListItem>?,
                val getArticle_type_display: String,
                val article_type_fk: String,
                val created_readable: String,
                val subscription_package: List<SubscriptionPackageItem>?,
                val publish_date_readable: String,
                val description: String,
                val title: String,
                val featured_image: List<FeaturedImageItem>?,
                val get_status_display: String,
                val video_content: String,
                val featured_article: Boolean,
                val report_slug:String,
                val publish_date: String,
                val slug: String,
                val content_code: String,
                val status: Int,
                val authors: List<AuthorsItem>?)


data class AuthorsItem(val full_name: String,
                       val profile_slug: String,
                       val picture: String)

data class FeaturedImageItem(val image_file: String)

data class RegionListItem(val name: String,
                          val slug: String)

data class SubscriptionPackageItem(val name: String,
                                   val id: Int)