package com.example.roomdbsample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.roomdbsample.destinations.*
import com.example.roomdbsample.model.Contact
import com.example.roomdbsample.ui.AddContactViewModel
import com.example.roomdbsample.ui.ContactsListViewModel
import com.example.roomdbsample.ui.theme.RoomDBSampleTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomDBSampleTheme {
                // A surface container using the 'background' color from the theme
//                PersonListScreen(DestinationsNavHost(navGraph = NavGraphs.root))

                DestinationsNavHost(navGraph =NavGraphs.root )
            }
        }
    }

}

@Destination
@Composable
fun HomeScreen(nav:DestinationsNavigator ,viewModel: ContactsListViewModel = hiltViewModel()) {


}

@Destination
@Composable
fun CreateNotes(nav:DestinationsNavigator,viewModel: AddContactViewModel = hiltViewModel(), viewModel2: ContactsListViewModel = hiltViewModel()) {

    Log.d("hh",viewModel2.getContacts().toString())

    var titleField by remember {
        mutableStateOf("")
    }
    var emailField by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()

            .background(color = Color(0xFFE0B27E))
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { },
                modifier = Modifier
                    .padding(start = 247.dp)
                    .clip(shape = CircleShape),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color(0xFFE0B27E)
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.info_outline),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)

                )

            }
            Button(
                onClick = {
                    var contact = Contact(titleField, emailField)

                    viewModel.addContact(contact)
                },
                modifier = Modifier
                    .padding(start = 25.dp)
                    .clip(shape = CircleShape),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,

                    contentColor = Color(0xFFE0B27E)
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.save),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)

                )

            }
        }
        TextField(
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFE0B27E)
            ),
            value = titleField,
            onValueChange = { titleField = it },
            placeholder = {
                Text(
                    text = "Title",
                    fontSize = 35.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            modifier = Modifier.fillMaxWidth()


        )
        TextField(
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFE0B27E)
            ),
            value = emailField,
            onValueChange = { emailField = it },
            placeholder = {
                Text(
                    text = "Type something...",
                    fontSize = 20.sp,
                    color = Color.White,

                    )
            },

            )


    }
}
@Destination(start = true)
@Composable
fun notes(nav:DestinationsNavigator, viewModel: AddContactViewModel = hiltViewModel()
          , viewModel2: ContactsListViewModel = hiltViewModel()) {


    if (viewModel2.contacts.size > 0) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Text(text="Notes", fontSize =35.sp, modifier = Modifier.padding(start=150.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp)
            ) {
                items(viewModel2.contacts) { contact->
                    val color = (Math.random() * 16777315).toInt() or (0xFF shl 24)

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp,top=35.dp),
                        RoundedCornerShape(3.dp)
                    ) {
                        Column(
                            modifier = Modifier
//                                .fillMaxWidth()
                                .fillMaxSize()
//                                .padding(vertical = 0.dp)
                                .clickable {
                                    nav.navigate(noteDestination(contact))
                                }
                                .background(Color(color))


                        ) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = contact.name,
                                fontSize = 35.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 12.dp)
                            )


                        }
                        Button(
                            onClick = {
                                viewModel2.delContacts(contact)
                            },
                            modifier = Modifier
                                .padding(top = 19.dp, start = 320.dp)
                                .clip(shape = CircleShape),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.White,
                                contentColor = Color(0xFFFA6A55)
                            )
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.del),
                                contentDescription = null,
                                modifier = Modifier.size(35.dp)

                            )

                        }
                    }

                }
            }
        }
    } else {
//        nav.navigate(HomeScreenDestination)
        Box(
            modifier = Modifier
                .background(color = Color(0xFFE0B27E))
                .fillMaxSize()
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Notes",
                    fontStyle = FontStyle.Italic,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(start = 8.dp, top = 7.dp)
                )
//        Spacer(modifier = Modifier.size())

                Button(
                    onClick = { }, modifier = Modifier
                        .padding(start = 160.dp, top = 7.dp)
                        .clip(shape = CircleShape)
                        .size(53.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = Color(0xFFFA6A55)
                    )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.search),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)

                    )

                }
                Button(
                    onClick = {}, modifier = Modifier
                        .padding(start = 23.dp, top = 7.dp)
                        .clip(shape = CircleShape)
                        .size(53.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = Color(0xFFFA6A55)
                    )
                )
                {
                    Icon(
                        painter = painterResource(R.drawable.info_outline),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)

                    )

                }
            }
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 53.dp, top = 163.dp)
            )
            {
                Image(
                    painter = painterResource(R.drawable._038766),
                    contentDescription = null,
                    modifier = Modifier
                        .size(290.dp)
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .clip(shape = CircleShape),

                    )
                Text(
                    text = "Create your first note !",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(start = 12.dp)
                )

            }
            Button(
                modifier = Modifier.padding(top = 580.dp, start = 312.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color(0xFFFA6A55)
                ),
                onClick = {
                    nav.navigate(CreateNotesDestination)
                }) {
                Icon(
                    painter = painterResource(R.drawable.create),
                    contentDescription = null,
                    modifier = Modifier.size(35.dp)

                )
            }
        }
    }
}




@Destination
@Composable
fun note (nav: DestinationsNavigator,contact: Contact) {
   var  viewModel2: ContactsListViewModel = hiltViewModel()
    Box(modifier = Modifier
        .padding(top = 0.dp)
        .fillMaxSize()
        .background(color = Color(0xFFE0B27E))
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {  },
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(40.dp)
                    .padding(top = 2.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color(0xFFE0B27E)
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.vector),
                    contentDescription = null,
                    modifier = Modifier.size(23.dp)

                )

            }

            Button(
                onClick = { nav.navigate(editNoteDestination(contact))},
                modifier = Modifier
                    .padding(start = 300.dp, top = 2.dp)
                    .clip(shape = CircleShape)
                    .size(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color(0xFFE0B27E)
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.mode),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)

                )

            }
        }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, top = 55.dp)) {

                Text(text = contact.name, fontSize = 30.sp, color = Color.White, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = contact.phone, fontSize = 25.sp, color = Color.White)
            }

    }
}
@Destination
@Composable
fun editNote(nav: DestinationsNavigator,contact: Contact){

//    @Composable
//    fun MyUI() {
//        if (dialogOpen) {
//            AlertDialog(
//                onDismissRequest = {
//
//                    dialogOpen = false
//                },
//                confirmButton = {
//                    TextButton(onClick = {
//                        dialogOpen = false
//                    }) {
//                        Text(text = "Save")
//                    }
//                },
//                dismissButton = {
//                    TextButton(onClick = {
//                        dialogOpen = false
//                    }) {
//                        Text(text = "Dismiss")
//                    }
//                },
//                title = {
//                    Text(text = "Title")
//                },
//                text = {
//                    Text(text = "Description")
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(32.dp),
//                shape = RoundedCornerShape(5.dp),
//                backgroundColor = Color.White,
//                properties = DialogProperties(
//                    dismissOnBackPress = true,
//                    dismissOnClickOutside = true
//                )
//            )
//        }
//
//    }
    var titleField by remember {
        mutableStateOf("")
    }
    var emailField by remember {
        mutableStateOf("")
    }
    Box(modifier = Modifier
        .padding(top = 0.dp)
        .fillMaxSize()
        .background(color = Color(0xFFE0B27E)))
    {
        Row(modifier = Modifier.fillMaxWidth()) {
            val openDialog = remember { mutableStateOf(false) }

            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    text = {
                        Text(
                            "Save changes ?", fontSize = 15.sp, fontWeight = FontWeight.Bold
                        )
                    },
                    buttons = {
                        Row(
                            modifier = Modifier
                                .padding(all = 10.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Button(
                                onClick = { openDialog.value = false }
                            ) {
                                Text("Save")
                            }
                            Button(

                                onClick = { openDialog.value = false }
                            ) {
                                Text("Discard")
                            }
                        }
                    }
                )
            }

            Button(onClick = {
                openDialog.value = true

               }
               , modifier = Modifier
                .padding(start = 260.dp)
                .clip(shape = CircleShape)
                .size(53.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color(0xFFE0B27E))) {
                Icon(
                    painter = painterResource(R.drawable.save),
                    contentDescription = null,
                    modifier = Modifier.size(35.dp)

                )

            }
            Button(onClick = { nav.navigate(noteDestination(contact)) }, modifier = Modifier
                .padding(start = 23.dp)
                .clip(shape = CircleShape)
                .size(53.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color(0xFFE0B27E)) )
            {
                Icon(
                    painter = painterResource(R.drawable.visibility),
                    contentDescription = null,
                    modifier = Modifier.size(35.dp)

                )

            }
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, top = 55.dp)) {
            TextField(
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xFFE0B27E)
                ),
                value = titleField,
                onValueChange = { titleField = it },
                placeholder = {
                    Text(
                        text = contact.name,
                        fontSize = 35.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                modifier = Modifier.fillMaxWidth()


            )
            TextField(
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xFFE0B27E)
                ),
                value = emailField,
                onValueChange = { emailField = it },
                placeholder = {
                    Text(
                        text =contact.phone,
                        fontSize = 20.sp,
                        color = Color.White,

                        )
                },

                )

//            TextField(Text = contact.name, fontSize = 30.sp, color = Color.White, fontWeight = FontWeight.Bold)
//            Spacer(modifier = Modifier.height(20.dp))
//            Text(text = contact.phone, fontSize = 25.sp, color = Color.White)
        }
    }

}
