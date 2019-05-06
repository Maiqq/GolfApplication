package jamk.fi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.MovementMethod
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dogInfoTextView.movementMethod = ScrollingMovementMethod()
        showDogData(0)
    }

    val breed = arrayOf("Welsh Corgi Cardigan", "Flat-coated Retriever", "Jack Russell Terrier")
    val size = arrayOf("Medium", "Large", "Small")
    val info = arrayOf("The Cardigan is a long, low dog with upright ears and a fox brush tail. The old American Kennel Club standard called it an Alsatian on short legs. ardigans, which are double coated, come in a variety of colors including any shade of red, sable, or brindle, as well as black, with or without tan, brindle or blue merle, with or without tan or brindle points. Other unofficial colors can occur, such as red merle, but these colors are not considered acceptable per the Cardigan standard. They usually have white on the neck, chest, legs, muzzle, underneath, tip of the tail and as a blaze on the head, known as the Irish pattern.  Other markings include ticking on the legs and muzzle, smutty muzzles and monk's hoods, especially on sables (a pattern of darker tipped hairs over a basic red coat color.)[citation needed]. An average Cardigan is around 10 to 13 inches (250 to 330 mm) tall at the withers and weighs from 30 to 38 pounds (14 to 17 kg) for the male and 25 to 34 pounds (11 to 15 kg) for the female. ife expectancy is 12–16 years.", "The Flat-Coated Retriever breed standard calls for males to be 23–25 in (58–64 cm) tall at the withers, with a recommended weight of 60–80 lb (27–36 kg),[1] and for females to be 22–24 in (56–61 cm), with a recommended weight of 55–75 lb (25–34 kg).[1][2] The Flat-Coated Retriever has strong muscular jaws and a relatively long muzzle. Its head is unique to the breed and is described as being \"of one piece\" with a minimal stop and a backskull of about the same length as the muzzle. It has almond-shaped, dark brown eyes with an intelligent, friendly expression. The ears are pendant, relatively small, and lie close to the head. The occiput (the bone at the back of the skull) is not to be accentuated (as it is in setters, for example) with the head flowing smoothly into a well-arched neck. The topline is strong and straight with a well-feathered tail of moderate length held straight off the back. This breed should be well-angulated front and rear, allowing for open, effortless movement. It is lighter, racier, and more elegant in appearance than the other retriever breeds.", "The Jack Russell Terrier is a small terrier that has its origins in fox hunting in England. It is principally white-bodied and smooth, rough or broken-coated and can be any colour.\n" +
            "\n" +
            "The Jack Russell is frequently confused with the Parson Russell terrier (see the American Kennel Club) and the Russell terrier, which is a shorter-legged, stockier variety. (Within the Fédération Cynologique Internationale, the \"Russell terrier\" is also known as \"Jack Russell terrier\".) The term \"Jack Russell\" is also commonly misapplied to other small white terriers. The Jack Russell is a broad type, with a size range of 10–15 inches (25–38 cm). The Parson Russell is limited only to a middle range with a standard size of 12–14 inches (30–36 cm), while the Russell terrier is smaller at 8–12 inches (20–30 cm). Each breed has different physical proportions according to the standards of their breed clubs.\n" +
            "\n" +
            "Jack Russells are an energetic breed that rely on a high level of exercise and stimulation. They are relatively free from any serious health complaints. Originating from dogs bred and used by Reverend John Russell in the early 19th century, from whom the breed takes its name, the Jack Russell has similar origins to the modern Fox terrier. It has gone through several changes over the years, corresponding to different use and breed standards set by kennel clubs. Recognition by kennel clubs for the Jack Russell breed has been opposed by the breed's parent societies – which resulted in the breeding and recognition of the Parson Russell terrier. Jack Russells have appeared many times in film, television, and print – with several historical dogs of note.")

    fun showDogData(index: Int){
        breedTextView.text = breed[index]
        sizeTextView.text = size[index]
        dogInfoTextView.text = info[index]

        //image
        var id = 0
        if (index == 0) id = R.drawable.cardigan
        else if (index == 1) id = R.drawable.flatcoated
        else if (index == 2) id = R.drawable.jackrussel
        imageView.setImageResource(id)
    }

    fun numberClicked(view: View?){
        val text = (view as TextView).text.toString()
        val int = text.toInt()-1
        showDogData(int)
    }
}
