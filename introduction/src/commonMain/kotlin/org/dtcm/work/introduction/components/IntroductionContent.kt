package org.dtcm.work.introduction.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.dtcm.work.design.normal100
import org.dtcm.work.design.small100
import org.dtcm.work.introduction.Res
import org.dtcm.work.introduction.ic_intro_one
import org.dtcm.work.introduction.ic_intro_three
import org.dtcm.work.introduction.ic_intro_two
import org.dtcm.work.introduction.introduction_enjoy_your_time_text
import org.dtcm.work.introduction.introduction_enjoy_your_time_title
import org.dtcm.work.introduction.introduction_sales_info_text
import org.dtcm.work.introduction.introduction_sales_info_title
import org.dtcm.work.introduction.introduction_welcome_text
import org.dtcm.work.introduction.introduction_welcome_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun IntroductionContent(
    page: Int,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column {
            val imageResource = when (page) {
                0 -> Res.drawable.ic_intro_one
                1 -> Res.drawable.ic_intro_two
                2 -> Res.drawable.ic_intro_three
                else -> Res.drawable.ic_intro_one
            }
            val textTitle = when (page) {
                0 -> Res.string.introduction_welcome_title
                1 -> Res.string.introduction_sales_info_title
                2 -> Res.string.introduction_enjoy_your_time_title
                else -> Res.string.introduction_welcome_title
            }
            val textBody = when (page) {
                0 -> Res.string.introduction_welcome_text
                1 -> Res.string.introduction_sales_info_text
                2 -> Res.string.introduction_enjoy_your_time_text
                else -> Res.string.introduction_welcome_text
            }
            Spacer(modifier = Modifier.height(normal100))
            Image(
                painter = painterResource(imageResource),
                contentDescription = null,
                modifier = Modifier.padding(start = small100),
            )
            Spacer(modifier = Modifier.height(normal100))
            Text(
                text = stringResource(textTitle),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(normal100))
            Text(
                text = stringResource(textBody),
                textAlign = TextAlign.Center,
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(start = normal100, end = normal100),
            )
            Spacer(modifier = Modifier.height(normal100))
        }
    }

}