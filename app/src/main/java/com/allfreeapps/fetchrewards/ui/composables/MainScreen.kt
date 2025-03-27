package com.allfreeapps.fetchrewards.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.allfreeapps.balanser_tool.ui.theme.My_Theme
import com.allfreeapps.fetchrewards.model.Reward
import com.allfreeapps.fetchrewards.viewModel.FetchRewardsViewModel

class MainScreen {


    @Composable
    fun FetchRewardsApp(
        innerPadding: PaddingValues,
        viewModel: FetchRewardsViewModel
    ){
        val rewardsResponse: List<Reward>? = viewModel.rewardsResponse.observeAsState().value

        Column(
            modifier= Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween

        ) {
            Box {
                ElevatedButton(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .padding(16.dp),
                    onClick = { viewModel.getRewards() }
                ) {
                    Text("Click to see the list")
                }
            }
            Box(modifier = Modifier.fillMaxSize()){
                if (!rewardsResponse.isNullOrEmpty()) {
                    RewardList(rewardsResponse)
                } else {
                    Text(text = "No text available",
                        modifier = Modifier.align(Alignment.Center))
                }
            }
        }


    }

    @Composable
    fun RewardList(response: List<Reward>) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(response.size) { index ->
                MessageRow(response[index])
                HorizontalDivider(thickness = 2.dp)
            }
        }
    }

    private @Composable
    fun MessageRow(reward: Reward) {
        Row {
            Text(text = "Name: ${reward.name}, Id: ${reward.id}, ListId: ${reward.listId}")
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewFetchRewardsApp(){
        My_Theme {
            FetchRewardsApp(innerPadding = PaddingValues(15.dp), viewModel = FetchRewardsViewModel())
        }
    }
}