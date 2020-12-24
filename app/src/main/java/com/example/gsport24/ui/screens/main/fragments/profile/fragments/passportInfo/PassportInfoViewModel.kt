package com.example.gsport24.ui.screens.main.fragments.profile.fragments.passportInfo

import android.graphics.Bitmap
import com.example.gsport24.Application
import com.example.gsport24.domain.useCase.passportInfo.UpdatePassportInfoUseCase
import com.example.gsport24.mvvm.vm.BaseViewModel
import com.example.gsport24.root.ext.toByteArray
import com.example.gsport24.ui.commands.Commands
import com.example.gsport24.ui.screens.main.fragments.profile.fragments.passportInfo.di.PassportInfoComponent
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class PassportInfoViewModel : BaseViewModel() {

	private val list : ArrayList<MultipartBody.Part> by lazy { ArrayList() }

	@Inject
	lateinit var useCase : UpdatePassportInfoUseCase

	init {
		inject()
	}

	override fun inject() {
		Application.getInstance().injectionStorage.get<PassportInfoComponent>().inject(this)
	}

	override fun releaseInjection() {
		Application.getInstance().injectionStorage.release<PassportInfoComponent>()
	}

	fun changePassportInfo(issuerId : String, passportNumber : String, givenDate : String) {
		launchDefault {
			val builder = MultipartBody.Builder()
			builder.addFormDataPart("passportIssueDate", givenDate)
			builder.addFormDataPart("passportIssuedBy", issuerId)
			builder.addFormDataPart("passportNumber", passportNumber)
			list.forEach {
				builder.addPart(it)
			}
			val result = useCase.changePassportInfo(builder.build().parts())
			result doOnSuccess {
				_viewCommands.postValue(Commands.PassportInfoUpdated)
			}
		}
	}

	fun createFile(bitmap : Bitmap, cacheDir : File) {
		launchIO {
			val file = File(cacheDir, "${UUID.randomUUID()}.png")
			file.createNewFile()

			try {
				val fileOutputStream = FileOutputStream(file)
				fileOutputStream.write(bitmap.toByteArray())
				fileOutputStream.flush()
				fileOutputStream.close()
			} catch (e : Exception) {

			}

			val reqFile : RequestBody = RequestBody.create(MediaType.parse("image/*"), file)
			list.add(MultipartBody.Part.createFormData("files", file.name, reqFile))
		}
	}
}