package bravo.carlos.alkewalletfinal.viewmodel

import android.app.Application
import bravo.carlos.alkewalletfinal.model.AccountResponse
import bravo.carlos.alkewalletfinal.model.UserResponse


//Clase global para guardar datos mientras la app esta abierta!
class GlobalClassApp : Application() {


    companion object{
        //vamos a crear un objeto usuario que estara global al proyecto
        var userLogged : UserResponse? = null

        var tokenAccess : String? = null

        var userAccount: AccountResponse? = null // Agregar la cuenta del usuario
    }

    override fun onCreate() {
        super.onCreate()
        userLogged = null
        tokenAccess = null
    }

}