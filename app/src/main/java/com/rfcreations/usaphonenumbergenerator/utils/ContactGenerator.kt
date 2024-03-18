package com.rfcreations.usaphonenumbergenerator.utils

import android.content.Context
import com.rfcreations.usaphonenumbergenerator.R
import com.rfcreations.usaphonenumbergenerator.data.States
import com.rfcreations.usaphonenumbergenerator.data.realNames
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.random.Random

class ContactGenerator(
    private val context: Context,
    private val selectedStates: List<String>,
    private val amountToGenerate: Int,
    private val selectedNameFormat: Int
) {

    private val states =
        listOf(
            States(
                context.getString(R.string.california),
                listOf(
                    209, 213, 310, 408, 415, 510, 530, 559, 619, 626, 650,
                    707, 714, 760, 805, 818, 831, 909, 916, 951
                )
            ),
            States(
                context.getString(R.string.texas),
                listOf(210, 214, 254, 325, 409, 432, 512, 713, 806, 817, 903, 915)
            ),
            States(
                context.getString(R.string.florida),
                listOf(239, 305, 321, 352, 407, 561, 727, 813, 850, 904, 941)
            ),
            States(
                context.getString(R.string.new_york),
                listOf(212, 315, 518, 585, 607, 631, 716, 718, 845, 914)
            ),
            States(
                context.getString(R.string.pennsylvania),
                listOf(215, 412, 484, 570, 610, 717, 724, 814)
            ),
            States(
                context.getString(R.string.illinois),
                listOf(217, 224, 309, 312, 618, 630, 708, 773, 815)
            ),
            States(context.getString(R.string.ohio), listOf(216, 330, 419, 513, 614, 740, 937)),
            States(context.getString(R.string.georgia), listOf(229, 404, 478, 678, 706, 770, 912)),
            States(
                context.getString(R.string.north_carolina),
                listOf(336, 704, 828, 910, 919, 980)
            ),
            States(
                context.getString(R.string.michigan),
                listOf(248, 269, 313, 517, 586, 616, 734, 810, 906, 989)
            )
        )

    fun generateAndSaveContactsAsVcf(): File {
        val contacts = StringBuilder()
        repeat(amountToGenerate) {
            val phoneNumber = createPhoneNumber()
            when (selectedNameFormat) {
                //Use phone number as name
                0 -> {
                    contacts.appendLine(
                        vCard(
                            phoneNumber,
                            phoneNumber,
                            ""
                        )
                    )

                }
                //Use realistic names
                1 -> {
                    contacts.appendLine(
                        vCard(
                            createPhoneNumber(),
                            realNames.random(),
                            realNames.random()
                        )
                    )
                }
                //Use random names
                else -> {
                    contacts.appendLine(
                        vCard(
                            phoneNumber,
                            getRandomName(),
                            getRandomName()
                        )
                    )
                }
            }
        }
        val contactFile = getUniqueContactFileName()

        //withContext(Dispatchers.IO) {
        FileWriter(contactFile).use {
            it.write(contacts.toString())
            it.flush()
            it.close()
        }
        // }
        return contactFile
    }

    private fun getUniqueContactFileName(): File {
        var count = 0
         val contactsFolderName = Constants.CONTACTS_FOLDER_NAME

        val exportDir = File(context.filesDir, "Contacts")
        if (!exportDir.exists()) exportDir.mkdirs()

        //format the date and time into readable form
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val creationDate = format.format(Calendar.getInstance().time)

        val states = selectedStates.joinToString()
        var file = File(exportDir, "$contactsFolderName $creationDate$states.vcf")
        //to make sure that the file doesn't exist
        while (file.exists()) {
            count++
            file = File(exportDir, "Contacts$creationDate(${if (count == 0) "" else 0}).vcf")
        }
        return file
    }

    private fun vCard(phoneNumber: String, firstName: String, lastName: String): String {
        return """
        BEGIN:VCARD
        VERSION:3.0
        N:$lastName;$firstName;;;
        FN:$firstName $lastName
        TEL;TYPE=cell,voice:$phoneNumber
        END:VCARD
    """.trimIndent()

    }

    private fun createPhoneNumber(): String {
        val filteredStates = states.filter { it.name in selectedStates }
        val randomAreaCode = filteredStates.random().areaCodes.random()
        val centralOfficeNumber = Random.nextInt(100, 1000)
        val lastFourDigits = StringBuilder()
        while (lastFourDigits.length < 4) {
            lastFourDigits.append(Random.nextInt(10))
        }
        return "+1($randomAreaCode)$centralOfficeNumber-$lastFourDigits"
    }

    private fun getRandomName(): String {
        val alphabets = 'a'..'z'
        return buildString {
            repeat(Random.nextInt(4, 11)) {
                append(alphabets.random())
            }
        }
    }
}


