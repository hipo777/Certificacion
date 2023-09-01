package org.demre.certificacion.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ListaHeroesEntity::class, DetalleHeroesEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseHeroe : RoomDatabase() {

    abstract fun getHeroesDao(): DaoHeroe

    companion object {
        @Volatile
        private var INSTANCE: DatabaseHeroe? = null

        fun getDatabase(context: Context): DatabaseHeroe {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseHeroe::class.java,
                    "db_heroes" // Nombre de la base de datos
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}