package com.example.cuadricula

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cuadricula.data.DataSource
import com.example.cuadricula.model.Topic
import com.example.cuadricula.ui.theme.CuadriculaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CuadriculaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GridApp(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GridApp(modifier: Modifier = Modifier) {
    //GridCardRow(topic = Topic(R.string.architecture, 58, R.drawable.architecture))
    GridList(topic = DataSource.topics, modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp))
}

@Composable
@ExperimentalLayoutApi
fun GridCardRow(topic: Topic, modifier: Modifier = Modifier) {


        Card {
            Row {
                Box {
                    Image(
                        painter = painterResource(id = topic.imageResourceId),
                        contentDescription = stringResource( id = topic.stringResourceId ),
                        modifier = Modifier
                            .size(width = 68.dp, height = 68.dp)
                            .aspectRatio(1f),
                        contentScale = ContentScale.Crop
                    )
                }


                Column {
                    Text(text = stringResource(id = topic.stringResourceId),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)

                    )
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_grain),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .height(16.dp)
                        )
                        Text(
                            text = topic.numberOfCourses.toString(),
                            modifier = Modifier
                                .padding(start = 8.dp)

                        )
                    }

                }
            }
        }


}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GridList(topic:List<Topic>, modifier: Modifier = Modifier){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(topic){
            topic -> GridCardRow(topic = topic, modifier = Modifier)
        }

    }

}

@Preview(showBackground = true)
@Composable
@ExperimentalLayoutApi
fun GridCardPreview() {
    CuadriculaTheme {
        GridCardRow(topic = Topic(R.string.architecture, 58, R.drawable.architecture))
    }

}