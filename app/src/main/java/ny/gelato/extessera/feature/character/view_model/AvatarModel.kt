package ny.gelato.extessera.feature.character.view_model

import android.support.design.widget.BottomSheetDialog
import ny.gelato.extessera.base.BaseViewModel
import ny.gelato.extessera.base.BaseAvatar
import ny.gelato.extessera.data.model.character.Character

/**
 * Created by jord.goldberg on 6/8/17.
 *
 * @layout bottom_sheet_character_avatar.xml
 * @layout item_character_avatar.xml
 */

data class AvatarModel(
        override val name: String = "",
        val description: String = "",
        override var isInspired: Boolean = false,
        override var imagePath: String = "",
        override var imageUrl: String = ""

) : BaseAvatar, BaseViewModel() {

    var newImageUrl: String? = null

    constructor(char: Character) :
            this(char.name,
                    char.description(),
                    char.hasInspiration,
                    char.imagePath,
                    char.imageUrl)

    fun menu(): AvatarModel = copy().apply { action = Action.CONTEXT_MENU }

    fun toggleInspiration(): AvatarModel = copy(isInspired = !isInspired)

    fun setImageUrl(url: CharSequence) {
        if (url.isEmpty()) newImageUrl = null
        else newImageUrl = url.toString()
        notifyChange()
    }

    fun clearImage(sheet: BottomSheetDialog): AvatarModel {
        imagePath = ""
        imageUrl = ""
        newImageUrl = null
        notifyChange()
        sheet.dismiss()
        return this.copy().apply { action = Action.UPDATE }

    }

    fun updateImage(sheet: BottomSheetDialog): AvatarModel {
        sheet.dismiss()
        return this.copy().apply {
            this@AvatarModel.newImageUrl?.let { imageUrl = it }
            action = Action.UPDATE
        }
    }

    fun about(): AboutModel = AboutModel()

    fun experience(): ExpModel = ExpModel()

    fun goTo(): GoToModel = GoToModel()
}