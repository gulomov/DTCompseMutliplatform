import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.dtcm.work.database.data.ProductImages
import org.dtcm.work.database.data.ProductSizes

class Converters {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromImagesList(images: List<ProductImages>): String = json.encodeToString(images)

    @TypeConverter
    fun toImagesList(imagesJson: String): List<ProductImages> = json.decodeFromString(imagesJson)

    @TypeConverter
    fun fromSizesList(sizes: List<ProductSizes>): String = json.encodeToString(sizes)

    @TypeConverter
    fun toSizesList(sizesJson: String): List<ProductSizes> = json.decodeFromString(sizesJson)
}
