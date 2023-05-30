package com.aristidevs.appsis301.io.response

import com.aristidevs.appsis301.model.User


/*"success": true,
"data": {
    "user": {
    },
    "jwt": zQw
}*/
data class LoginResponse(
    val success: Boolean,
    val user: User,
    val jwt: String
)
