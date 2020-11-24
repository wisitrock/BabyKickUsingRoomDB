package th.ac.su.babykick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Locale;

public class Advice_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice_);


        TextView mTitleWindow = findViewById(R.id.titleWindow_advice);
        TextView mMessageWindow = findViewById(R.id.messageWindow_advice);

        mTitleWindow.setText(R.string.fatalmovement);

        StringBuilder stringBuilder = new StringBuilder();
        String someMessage=null;
        if(Locale.getDefault().getLanguage().equals("th")){
            Log.i("Language", "Language : " +  Locale.getDefault().getLanguage());
             someMessage = "  ในระยะแรก ของการตั้งครรภ์ทารกจะมีการดิ้น ในรูปแบบที่ต่างกัน โดยทารกจะดิ้นดีมาก แต่เป็นการดิ้นที่ระบบประสาท จะมีการประสานกันน้อยมาก\n" +
                    "\n" +
                    "   ในระยะหลัง ของการตั้งครรภ์ การดิ้นของทารก จะมีการประสานกันมากขึ้น\n" +
                    " \n" +
                    "\n" +
                    "ทารกในครรภ์จะมีช่วงหลับและตื่นไม่ตรงกันกับมารดา ช่วงระยะเวลานอนหลับของทารกต่อรอบนาน 20 นาที ถึง 2 ชั่วโมง นอกจากนี้แล้วทารกในครรภ์ยังมีการดิ้นในแต่ละช่วงเวลาของวันไม่เท่ากัน โดยพบว่าทารกจะดิ้นมากระหว่างเวลา 21.00 - 01.00 น. และจะดิ้นมากเมื่อมารดารับประทานอาหารเสร็จใหม่ๆ\n" +
                    "\n" +
                    "องค์ประกอบที่มีผลต่อการดิ้นของทารกในครรภ์   ได้แก่\n" +
                    "\n" +
                    "   • ระดับกลูโคสในเลือดมารดา\n" +
                    "   • มื้ออาหารที่มารดาได้รับ\n" +
                    "   • เสียงภายนอกที่มากระตุ้น\n" +
                    "   • อาชีพของมารดา\n" +
                    "   • ความสนใจของมารดาต่อการดิ้นของทารก\n" +
                    " \n" +
                    "\n" +
                    "ความสำคัญของการนับทารกดิ้นในครรภ์\n" +
                    "   การที่ทารกดิ้นน้อยลง มักเกิดร่วมกับภาวะขาดออกซิเจนจนเรื้อรัง และอยู่ในภาวะอันตราย ทารกดิ้นน้อยลงหรือหยุดดิ้นจะพบเป็นเวลาประมาณ 12 - 48 ชั่วโมง ก่อนที่จะเสียชีวิต ดังนั้นการนับทารกดิ้นจะช่วยในการตรวจ\n" +
                    "ค้นคว้า หรือแก้ไขภาวะที่อาจทำให้ทารกเสียชีวิต การที่คุณแม่รู้สึกว่าทารกในครรภ์ดิ้นน้อยลงนับเป็นสัญญาณอันตราย ต้องรีบมาพบแพทย์เพื่อตรวจวินิจฉัยต่อไปว่าทารกในครรภ์มีสุขภาพที่ไม่ดีจริงหรือไม่\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "ควรเริ่มนับทารกดิ้นในครรภ์เมื่อไหร่?\n" +
                    "   คุณแม่ควรนับการดิ้นของทารกในครรภ์ เมื่อตั้งครรภ์ประมาณ 28 สัปดาห์ขึ้นไป ทั้งนี้ขึ้นกับภาวะแทรกซ้อนที่อาจพบระหว่างตั้งครรภ์\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "วิธีการนับการดิ้นของทารกในครรภ์\n" +
                    "   เริ่มนับทารกดิ้นในครรภ์ตั้งแต่ 09.00 น. โดยนับครบ 10 ครั้ง ใช้เวลาในการนับประมาณ 10-12 ชั่วโมง ถ้านับได้ 10 ครั้งขึ้นไปถือว่าทารกปกติ คุณแม่ควรนับและบันทึกการดิ้นของทารกแต่ละครั้งลงตาราง การนับการดิ้นของทารกแต่ละครั้งให้บันทึกไว้จนครบ 10 ครั้ง ถ้าครบ 12 ชั่วโมง แล้วทารกดิ้นไม่ถึง 10 ครั้ง ให้รีบมาพบแพทย์ทันที\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "ข้อแนะนำในการนับและบันทึกการดิ้นของทารก\n" +
                    "   • ลักษณะการดิ้นของทารก คือ เตะ ยืดตัว บิดตัว\n" +
                    "   • ท่าที่ดีที่สุดในการรับรู้ถึงการดิ้นของทารก คือ ท่านอนตะแคงซ้าย\n" +
                    "   • ขณะบันทึกคุณแม่ควรอยู่ในที่เงียบ เพราะจะทำให้มีสมาธิในการรับรู้ถึงการดิ้นของทารกได้ดี\n" +
                    "   • ต้องบันทึกการดิ้นของทารกทุกวัน\n" +
                    "   • โปรดนำแบบบันทึกการดิ้นของทารกมาให้แพทย์หรือพยาบาลดูทุกครั้งที่มาฝากครรภ์\n" +
                    " \n" +
                    "\n" +
                    "วิธีนับลูกดิ้น\n" +
                    "   • ครั้งแรก ตอนเช้าในขณะที่คุณแม่ยังไม่มีงานอะไรมากนัก\n" +
                    "   • ครั้งที่สอง ตอนเย็นหรือตอนค่ำๆ ขณะที่ลูกน้อยเคลื่อนไหวแรง เริ่มจับเวลาแล้วนับดูว่า ลูกน้อยดิ้นครบ 10 ครั้ง ในเวลาเท่าใดแล้วจดเวลาเอาไว้ (จำนวนครั้งของการดิ้นจะนับรวม ทั้งการเตะ, การเอาศอกมากระทุ้ง, การบิดตัวของลูกน้อย) \n" +
                    " \n" +
                    "       - บางรายลูกจะดิ้นครบ 10 ครั้ง ภายในเวลาไม่ถึง 10 นาที ก็ไม่ต้องกังวลว่าผิดปกติ อาจจะเป็นวันที่ลูกดิ้นมาก ถ้าลูกดิ้นไม่ถึง 10 ครั้งใน 1 ชั่วโมง ให้คุณแม่ดื่มนม หรือทานอาหารว่าง แล้วนอนพักเริ่มนับการดิ้นของลูกน้อยใหม่ ถ้าใน 1 ชั่วโมง ลูกยังดิ้นไม่ครบ 10 ครั้งให้นับต่อไปและจดไว้ว่าดิ้นครบ 10 ครั้งในเวลากี่ชั่วโมง\n" +
                    "       - ถ้าหากครบ 12 ชั่วโมง แล้วลูกยังดิ้นไม่ครบ 10 ครั้ง ให้คุณแม่รีบไปพบแพทย์ แพทย์อาจจะใช้เครื่องฟังเสียง ฟังเสียงหัวใจหรือตรวจอัลตราซาวนด์ หรือใช้เครื่องตรวจสภาพของลูกน้อยในครรภ์ เพื่อดูว่าผิดปกติหรือไม่ต่อไป";
        }else if(Locale.getDefault().getLanguage().equals("en")){
            Log.i("Language", "Language : " +  Locale.getDefault().getLanguage());
            someMessage = " In the early stages of pregnancy, the baby will have to flex. In different ways The baby will flex very well. But it's a flex on the nervous system There will be very little harmonization.\n" +
                    "\n" +
                    "   In the later stages of pregnancy Baby flexion There will be more harmonization\n" +
                    " \n" +
                    "\n" +
                    "The fetus has different sleep and wake periods with the mother. The infant's sleep duration per cycle is 20 minutes to 2 hours. Additionally, the fetus struggles at different times of the day. It was found that the baby struggled a lot between 21.00 - 01.00 and would wrestle when the mother finished the meal.\n" +
                    "\n" +
                    "Elements that affect fetal flexion are:\n" +
                    "\n" +
                    "   • Maternal blood glucose level\n" +
                    "   • Meals received by mothers\n" +
                    "   • External sounds that come to stimulate\n" +
                    "   • Mother's occupation\n" +
                    "   • Maternal interest in the baby's wriggles\n" +
                    " \n" +
                    "\n" +
                    "The importance of counting fetuses in the womb\n" +
                    "   The baby squirming Often occurs with chronic hypoxia And in danger Babies wriggling less or stopping to wrestle around 12 to 48 hours before death. Therefore, counting the baby squirm will help in the examination.\n" +
                    "Researching or correcting conditions that can cause the death of the baby It is a danger sign that the mother feels less wriggling the fetus. Have to come to the doctor for further diagnosis whether the fetus is really bad or not.\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "When should I start counting my fetuses?\n" +
                    "   The mother should count the fetal struggles. At about 28 weeks of pregnancy. This depends on the complications that may be encountered during pregnancy.\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "How to count fetal flexion\n" +
                    "   Start counting the fetus at 9:00 am, counting all 10 times, taking about 10-12 hours, if counted 10 times or more, it is considered normal baby. The mother should count and record each stretch of the baby on the table. The count of each stretch of the baby is recorded for 10 times. If 12 hours have passed and the baby struggles less than 10 times, see a doctor immediately.\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Tips for counting and recording your baby's stretches\n" +
                    "   • The baby's movement is kick, stretch, twist.\n" +
                    "   • The best posture to recognize a baby's dance is the left lying position.\n" +
                    "   • While recording, Mother should remain in a quiet place. This will allow you to concentrate on the baby's perception as well.\n" +
                    "   • The baby's struggles must be recorded every day.\n" +
                    "   • Please bring the baby's flex diary to the doctor or nurse every time you visit the antenat.\n" +
                    " \n" +
                    "\n" +
                    "How to count the ball\n" +
                    "   • First time in the morning, while my mother doesn't have much work yet.\n" +
                    "   • Second time in the evening or in the evening While the baby moves hard Start timing and count to see if How much time does your child wrestle 10 times and write down the time? (The number of squirms will count including kicks, elbow jabs, twisting of your baby)\n" +
                    " \n" +
                    "       - In some cases, the child will wrestle 10 times in less than 10 minutes, so you don't have to worry about anything unusual It may be a day when the child struggles a lot. If the baby wriggles less than 10 times in an hour, let your mother drink milk. Or have a snack Then lie down and start counting the new baby's struggles. If in an hour, the baby is still struggling 10 times, continue to count and write down that 10 wrongs in how many hours.\n" +
                    "       - If 12 hours have passed and the baby is still struggling 10 times, mother should seek medical attention. The doctor may use a stethoscope. Listen to the heart sound or check ultrasound Or using a device to check the condition of the unborn baby To see if it is wrong or not next";
        }





        stringBuilder.append(someMessage);

        mMessageWindow.setText(stringBuilder.toString());
    }
}