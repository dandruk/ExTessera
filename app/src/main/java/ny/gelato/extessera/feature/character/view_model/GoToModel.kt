package ny.gelato.extessera.feature.character.view_model

import android.support.design.widget.BottomSheetDialog
import ny.gelato.extessera.base.BaseViewModel

/**
 * Created by jord.goldberg on 6/21/17.
 *
 * @layout bottom_sheet_character_go_to.xml
 */

data class GoToModel(
        var destination: Destination = GoToModel.Destination.NONE

) : BaseViewModel() {

    enum class Destination {
        NONE,
        NOTES,
        ABILITIES,
        SAVES,
        SKILLS,
        WEAPONS,
        SPELLS,
        EQUIPMENT
    }

    fun goTo(destination: Destination, sheet: BottomSheetDialog): GoToModel {
        this.destination = destination
        sheet.dismiss()
        return this
    }
}