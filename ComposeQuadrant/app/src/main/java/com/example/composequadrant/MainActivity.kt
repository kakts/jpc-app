package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            ComposeQuadrantTheme {
                ComposeQuadrant()
            }
        }
    }
}

@Composable
fun ComposeQuadrant() {
    ComposeQuadrantTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxSize()
                    .weight(1f, true),
                horizontalArrangement = Arrangement.Center
                ) {
                Grid(
                    color = colorResource(R.color.top_left),
                    textTitle = stringResource(R.string.text_composable),
                    textDesc = stringResource(R.string.text_composable_desc),
                    modifier = Modifier.weight(1f, true)
                )
                Grid(
                    color = colorResource(R.color.top_right),
                    textTitle = stringResource(R.string.image_composable),
                    textDesc = stringResource(R.string.image_composable_desc),
                    modifier = Modifier.weight(1f, true)
                )

            }
            Row(
                modifier = Modifier.fillMaxSize()
                    .weight(1f, true),
                horizontalArrangement = Arrangement.Center
            ) {
                Grid(
                    color = colorResource(R.color.bottom_left),
                    textTitle = stringResource(R.string.row_composable),
                    textDesc = stringResource(R.string.row_composable_desc),
                    modifier = Modifier.weight(1f, true)
                )
                Grid(
                    color = colorResource(R.color.bottom_right),
                    textTitle = stringResource(R.string.column_composable),
                    textDesc = stringResource(R.string.column_composable_desc),
                    modifier = Modifier.weight(1f, true)
                )

            }
        }
    }
}

/**
 * 各象限のグリッド
 */
@Composable
fun Grid(color: Color, textTitle: String, textDesc: String, modifier: Modifier = Modifier) {
    Box (
        modifier = modifier
            .padding(0.dp)
            .fillMaxSize()
            .background(color = color),
        contentAlignment = Alignment.Center
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = textTitle,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp)
            )
            Text(
                text = textDesc,
                textAlign = TextAlign.Justify
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeQuadrantTheme {
        Grid(
            color = colorResource(R.color.top_left),
            textTitle = "I",
            textDesc = "第一象限"
        )
    }
}