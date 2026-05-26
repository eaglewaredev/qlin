package dev.eagleware.qlin

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import dev.eagleware.Qlin

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:Qlin.db")
        Qlin.Schema.create(driver)
        return driver
    }
}