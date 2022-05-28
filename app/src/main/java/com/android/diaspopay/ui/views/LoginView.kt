package com.android.diaspopay.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.diaspopay.R
import com.android.diaspopay.ui.views.model.Route

@Composable
@ExperimentalMaterial3Api
fun Login(navController: NavHostController, /*userViewModel: UserViewModel,*/ context: Any) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    val isLoading = remember { mutableStateOf(false) }

    fun showProgressBar() {
        isLoading.value = true
    }

    fun hideProgressBar() {
        isLoading.value = false
    }

    Column {
        if (isLoading.value) {
            AlertDialog(
                onDismissRequest = {
                    // Dismiss the dialog when the user clicks outside the dialog or on the back
                    // button. If you want to disable that functionality, simply use an empty
                    // onCloseRequest.
                    isLoading.value = false
                },
                title = {

                },
                text = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row() {
                            CircularProgressIndicator()
                            Row(Modifier.padding(10.dp)) {
                                Text(text = "Connexion en cours...")
                            }
                        }

                    }
                },
                confirmButton = {

                },
                dismissButton = {

                }
            )
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {


        Text(
            text = "DiaspoPay",
            modifier = Modifier
                .padding(top = 50.dp, bottom = 40.dp, start = 0.dp, end = 0.dp),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 40.sp
        )


        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(id = R.string.your_email)) },
            placeholder = { Text("") },
            leadingIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "",
                        tint = colorResource(R.color.Purple700)
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 0.dp, start = 30.dp, end = 30.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(id = R.string.your_password)) },
            visualTransformation =
            if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "",
                        tint = colorResource(R.color.Purple700)
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = { passwordHidden = !passwordHidden }) {
                    val visibilityIcon =
                        if (passwordHidden) painterResource(id = R.drawable.baseline_visibility_24)
                        else painterResource(id = R.drawable.baseline_visibility_off_24)
                    val description = if (passwordHidden) "Show password" else "Hide password"
                    Icon(painter = visibilityIcon, contentDescription = description)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 0.dp, start = 30.dp, end = 30.dp)
        )

        ClickableText(
            buildAnnotatedString {
                pushStringAnnotation(
                    tag = "",
                    annotation = ""
                )
                withStyle(
                    style = SpanStyle(
                        color = colorResource(R.color.Purple700),
                        fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline,
                        fontSize = 15.sp
                    )
                ) {
                    append("Mot de passe oublié ?")
                }

                pop()
            },
            onClick = {

            }, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 0.dp, start = 30.dp, end = 30.dp)
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 0.dp, start = 30.dp, end = 30.dp),
            onClick = {
                navController.navigate(Route.homeView)
                // Toast.makeText(context,"MALEO MALEO MALEO",Toast.LENGTH_LONG).show()
                // viewModelLogin(userViewModel, "sidneymaleoregis@gmail.com","Nfkol3324012020@!", context)
                // Log.d("Test1", "Here");
            }) {
            Text(stringResource(R.string.connexion), color = Color.White)
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 0.dp, start = 30.dp, end = 30.dp),
            onClick = {
                //navController.navigate("home")
                // Toast.makeText(context,"MALEO MALEO MALEO",Toast.LENGTH_LONG).show()
                // viewModelLogin(userViewModel, "sidneymaleoregis@gmail.com","Nfkol3324012020@!", context)
                // Log.d("Test1", "Here");
            }) {
            Text("Ouvrir un compte", color = Color.White)
        }


    }
}