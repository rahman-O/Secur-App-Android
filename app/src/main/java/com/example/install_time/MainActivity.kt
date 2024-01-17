package com.example.install_time

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.install_time.ui.theme.InstallTimeTheme
import com.github.javiersantos.piracychecker.PiracyChecker
import com.github.javiersantos.piracychecker.callback
import com.github.javiersantos.piracychecker.enums.Display
import com.github.javiersantos.piracychecker.enums.InstallerID
import com.github.javiersantos.piracychecker.enums.PirateApp
import com.github.javiersantos.piracychecker.utils.apkSignatures


class MainActivity : ComponentActivity() {
    private var piracyCheckerDisplay = Display.ACTIVITY
    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d("onCreate", "onCreate:${readSignature()} ")

        super.onCreate(savedInstanceState)
        setContent {
            InstallTimeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }

   /* fun verifyInstallerId(context: Context) {
        val app = PirateApp("PiracyChecker Library (Demo)", "com.github.javiersantos.piracychecker.demo")
        val deepCheck = true
        PiracyChecker(context).display(piracyCheckerDisplay)

            .enableEmulatorCheck(deepCheck)
            // The original APK signature for the PRODUCTION version
            // Additional configuration if needed
            //.enableSigningCertificates("VHZs2aiTBiap/F+AYhYeppy0aF0=") // Right signature

            .start()
    }
*/

    fun verifySignature(context: Context) {
        PiracyChecker(context).
            display(piracyCheckerDisplay)
            .enableSigningCertificates("478yYkKAQF+KST8y4ATKvHkYibo=") // Wrong signature
            //enableSigningCertificates("VHZs2aiTBiap/F+AYhYeppy0aF0=") // Right signature
        .start()
    }

    fun readSignature() {
        val dialogMessage = StringBuilder()
        apkSignatures.forEach {
            Log.e("Signature", it)
            dialogMessage.append("* ").append(it).append("\n")
        }
        AlertDialog.Builder(this)
            .setTitle("APK")
            .setMessage(dialogMessage.toString())
            .show()
    }

    fun verifyInstallerId(context: Context) {
        PiracyChecker(context)
            . display(piracyCheckerDisplay)
            . enableInstallerId(InstallerID.GOOGLE_PLAY)
        .start()
    }

    fun verifyUnauthorizedApps(context: Context) {
        PiracyChecker(context)
            .display(piracyCheckerDisplay)
            . enableUnauthorizedAppsCheck()
            //blockIfUnauthorizedAppUninstalled("license_checker", "block")
        .start()
    }

    fun verifyStores(context: Context) {
        PiracyChecker(context)
            . display(piracyCheckerDisplay)
            .enableStoresCheck()
        .start()
    }

/*    fun verifyDebug(context: Context) {
        PiracyChecker(context)
            . display(piracyCheckerDisplay)
            .enableDebugCheck()
            . callback {
                allow {
                    // Do something when the user is allowed to use the app
                }
                doNotAllow { piracyCheckerError, pirateApp ->
                    // You can either do something specific when the user is not allowed to use the app
                    // Or manage the error, using the 'error' parameter, yourself (Check errors at {@link PiracyCheckerError}).

                    // Additionally, if you enabled the check of pirate apps and/or third-party stores, the 'app' param
                    // is the app that has been detected on device. App can be null, and when null, it means no pirate app or store was found,
                    // or you disabled the check for those apps.
                    // This allows you to let users know the possible reasons why license is been invalid.
                }
                onError { error ->
                    // This method is not required to be implemented/overriden but...
                    // You can either do something specific when an error occurs while checking the license,
                    // Or manage the error, using the 'error' parameter, yourself (Check errors at {@link PiracyCheckerError}).
                }
            }
        .start()
    }*/

    fun verifyEmulator(context: Context) {
        PiracyChecker(context)
            .display(piracyCheckerDisplay)
            . enableEmulatorCheck(false)
        .start()
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Log.d("onCreate", "onCreate:1")
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InstallTimeTheme {
        Greeting("Android")
    }
}