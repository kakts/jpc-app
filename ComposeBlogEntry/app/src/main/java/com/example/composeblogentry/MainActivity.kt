package com.example.composeblogentry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeblogentry.ui.theme.ComposeBlogEntryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBlogEntryTheme {
                ComposeBlogEntry()
            }
        }
    }
}

@Composable
fun ComposeBlogEntry() {
    BlogEntryCard(
        title = stringResource(id = R.string.entry_title),
        summary = stringResource(id = R.string.entry_summary),
        text = stringResource(id = R.string.entry_text),
        imagePainter = painterResource(id = R.drawable.bg_compose_background)
    )
}

@Composable
fun BlogEntryCard(
    title: String,
    summary: String,
    text: String,
    imagePainter: Painter
) {
    Column(
    ) {
        Image(painter = imagePainter, contentDescription = null)
        Text(
            text = title,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(16.dp)
            )
        Text(
            text = summary,
            textAlign = TextAlign.Justify, // テキストの配置
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        Text(
            text = text,
            textAlign = TextAlign.Justify, // テキストの配置
            modifier = Modifier
                .padding(16.dp)
        )
    }



}


@Composable
fun HeaderImage(modifier: Modifier = Modifier) {
    // 画像の表示
    Image(
        painter = painterResource(id = R.drawable.bg_compose_background),
        contentDescription = "Header",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeBlogEntry()
}