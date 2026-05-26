package dev.eagleware.qlin

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dev.eagleware.Qlin

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver{
        return AndroidSqliteDriver(Qlin.Schema, context, "Qlin.db")
    }
}