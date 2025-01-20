package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonPage()
            }
        }
    }
}

@Composable
fun LemonPage() {
    Column(

    ) {
        LemonTitleBox()
        LemonContents(R.color.lemonade_green, R.string.desc_1, R.drawable.lemon_tree, "Lemonade")
    }
}

@Composable
fun LemonTitleBox() {
    Box(
        Modifier
            .background(colorResource(id = R.color.lemonade_yellow))
            .fillMaxWidth()

    ) {
        Text(
            text = "Lemonade",
            modifier = Modifier
                .padding(18.dp)
                .align(Alignment.Center),
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
        )
    }
}

@Composable
fun LemonContents(imgBgColor: Int, contentDescId: Int, imgId: Int, title: String) {
//    Box(
//        Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            // TODO 画像を角丸にする
            Box(
                Modifier.background(colorResource(id = imgBgColor))
                    .wrapContentSize()
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Image(
                    painter = painterResource(id = imgId),
                    contentDescription = stringResource(contentDescId),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .wrapContentSize()
                )

            }
            Text(
                text = title,
                modifier = Modifier.padding(18.dp).align(Alignment.CenterHorizontally),
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
//    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        LemonPage()
    }
}