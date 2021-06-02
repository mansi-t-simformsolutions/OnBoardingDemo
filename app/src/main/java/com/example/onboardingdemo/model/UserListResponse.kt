package com.example.onboardingdemo

data class UserListResponse(val `data`: List<UserData>,
                            val page: Int,
                            val per_page: Int,
                            val support: Support,
                            val total: Int,
                            val total_pages: Int)
