package com.animals.safety.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.animals.safety.R
import com.animals.safety.common.capitalizeFirstChar
import com.animals.safety.data.Animal
import com.animals.safety.data.AnimalData
import com.animals.safety.data.Breed
import com.animals.safety.ui.theme.AimantsDanimauxTheme
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  modifier: Modifier = Modifier,
  animals: List<Animal> = AnimalData.animals,
  onAnimalClick: (Animal) -> Unit = {},
  onFABClick: () -> Unit = {},
) {
  Scaffold(
    modifier = modifier,
    topBar = {
      TopAppBar(
        title = {
          Text(stringResource(id = R.string.home_fragment_label))
        }
      )
    },
    floatingActionButtonPosition = FabPosition.End,
    floatingActionButton = {
      FloatingActionButton(
        onClick = {
          onFABClick()
        }
      ) {
        Icon(
          imageVector = Icons.Filled.Add,
          contentDescription = stringResource(id = R.string.contentDescription_add_animal)
        )
      }
    }
  ) { contentPadding ->
    HomeList(
      modifier = modifier.padding(contentPadding),
      animals = animals,
      onAnimalClick = onAnimalClick
    )
  }
}

@Composable
private fun HomeList(
  modifier: Modifier = Modifier,
  animals: List<Animal>,
  onAnimalClick: (Animal) -> Unit,
) {
  LazyColumn(modifier) {
    items(animals) { animal ->
      HomeCell(
        animal = animal,
        onAnimalClick = onAnimalClick
      )
      HorizontalDivider()
    }
  }
}

@Composable
private fun HomeCell(
  animal: Animal,
  onAnimalClick: (Animal) -> Unit,
  modifier: Modifier = Modifier
) {
  //TODO: A compléter
//  Box(
//    modifier = Modifier
//      .fillMaxWidth()
//      .height(20.dp)
//      .clickable {
//        onAnimalClick(animal)
//      }
//  )
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
      .padding(start= 12.dp, end = 12.dp, top = 4.dp, bottom= 4.dp)
      .fillMaxWidth()
      .clickable { onAnimalClick(animal) }
  ){
    Image(
      painter = painterResource(animal.breed.cover),
      contentDescription = null,
      modifier = Modifier
        .size(70.dp)
        .padding(4.dp)
        .clip(RoundedCornerShape(4.dp))
    )
    Column(
      modifier = Modifier.padding(start= 12.dp, end = 12.dp, top = 4.dp, bottom= 4.dp)
    ){
      Text(
        text = animal.name,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
      )
      val animalName = animal.breed.name.lowercase().replaceFirstChar { it.uppercaseChar()}
      Text(
        text = capitalizeFirstChar(stringResource(
          R.string.information,
          stringResource(animal.breed.translatedName),
          animal.age,
          animal.weight,
          animal.height,
          )),
        fontSize = 16.sp,
      )
    }
  }
}



@Preview(showBackground = true)
@Composable
private fun HomeCellPreview() {
  AimantsDanimauxTheme(dynamicColor = false) {
    HomeCell(
      animal = Animal(UUID.randomUUID(),"Milou", Breed.DOG, 6, 23.2f, 42.4f),
      onAnimalClick = {}
    )
  }
}
