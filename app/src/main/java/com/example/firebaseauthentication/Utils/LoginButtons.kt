package com.example.firebaseauthentication.Utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firebaseauthentication.R


@Composable
fun LoginButtons(
    onClick: () -> Unit,
    image: Int,
    text: String,
    cardBackground: Color,
    textColor: Color
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth(),
        border = BorderStroke(2.dp, Black),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = cardBackground,
            contentColor = Unspecified
        ),
        elevation = CardDefaults.outlinedCardElevation(
            defaultElevation = 10.dp,
            pressedElevation = 20.dp,
            hoveredElevation = 15.dp,
            focusedElevation = 12.dp,
            draggedElevation = 25.dp
        ),
        onClick = onClick
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(image),
                null,
                modifier = Modifier
                    .padding(10.dp)
                    .size(30.dp),
                tint = Unspecified
            )
            Spacer(
                modifier = Modifier.padding(4.dp)
            )

            Text(
                text = text,
                modifier = Modifier,
                color = textColor,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
private fun DOEIDE() {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LoginButtons(
            image = R.drawable.google_logo, text = "Google", onClick = {

            },
            cardBackground = White, textColor = Black
        )
    }
}