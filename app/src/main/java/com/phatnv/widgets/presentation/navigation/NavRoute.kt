package com.phatnv.widgets.presentation.navigation

import com.phatnv.widgets.data.enum.APPROUTES

sealed class NavRoute(val path: String) {

    object Authentication : NavRoute(APPROUTES.AUTHENTICATION.name)

    object Login : NavRoute(APPROUTES.LOGIN.name)

    object Home : NavRoute(APPROUTES.HOME.name) {
        const val params = "params"
    }

    object Detail : NavRoute(APPROUTES.DETAIL.name) {
        const val id = "id"
        const val name = "name"
    }

    fun withArgs(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    fun withArgsFormat(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach { arg ->
                append("/{$arg}")
            }
        }
    }
}