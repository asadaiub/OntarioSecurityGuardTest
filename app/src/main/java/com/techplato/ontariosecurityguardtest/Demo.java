package com.techplato.ontariosecurityguardtest;

import com.techplato.ontariosecurityguardtest.DB.Question;

import java.util.ArrayList;

public class Demo {

    public static ArrayList<Question> getList(){
       ArrayList<Question> qList=new ArrayList<>();

        //Model
       /* qList.add(new Question(1
                , "ques"
                , "aa"
                , "bb"
                , "cc"
                , "dd"
                , "aa"
                ,1
                , 0,3, 0,0));*/


        qList.add(new Question(1
                , "What is the legal age of an adult in Canada?"
                , "18"
                , "19"
                , "21"
                , "25"
                , "18"
                ,1
                , 0,3, 0,0));

        qList.add(new Question(1
                , "Section 10 under the Canadian charter of rights and freedoms states:"
                , "Everyone has the right to be informed the reasons for their arrest or detention without reasonable delay."
                , "Everyone has the right to obtain legal counsel without reasonable delay."
                , "Everyone has the right to refuse unreasonable search and seizure."
                , "Everyone has the right to bear arms"
                , "Everyone has the right to be informed the reasons for their arrest or detention without reasonable delay."
                ,1
                , 0,3, 0,0));



        qList.add(new Question(1
                , "If a security guard had trouble hearing a radio transmission and needed the dispatcher to repeat the message, what 10 codes would the guard use?"
                , "10-0"
                , "10-1"
                , "10-3"
                , "10-9"
                , "10-9"
                ,1
                , 0,3, 0,0));

        qList.add(new Question(1
                , "According to national use of force guidelines what is the first response option?"
                , "Presence of security guards"
                , "Tactical communications"
                , "Passive aggression"
                , "Baton"
                , "Presence of security guards"
                ,1
                , 0,3, 0,0));

        qList.add(new Question(1
                , "While working as a security guard working on private property, on whose authority are you enforcing the rules on the site?"
                , "Federal and provincial law"
                , "Company policies and procedures"
                , "Trespass to property act"
                , "As an agent of the owner of the property"
                , "As an agent of the owner of the property"
                ,1
                , 0,3, 0,0));

        qList.add(new Question(1
                , "As a security guard working outside an establishment licensed to serve liquor under the Liquor License Act of Ontario you witness someone leaving the establishment and they appear to be intoxicated. What should you do?"
                , "Call the police"
                , "Arrest them for public drunkenness"
                , "Let the leave"
                , "Ask them if they have a safe ride home"
                , "Ask them if they have a safe ride home"
                ,1
                , 0,3, 0,0));


        qList.add(new Question(1
                , "Where would a security guard find answers to questions regarding the correct handling of evidence?"
                , "Canada Evidence Act and Ontario Evidence Act"
                , "Police Service Act"
                , "Criminal Code of Conduct"
                , "Forensics Act"
                , "Canada Evidence Act and Ontario Evidence Act"
                ,1
                , 0,3, 0,0));

        qList.add(new Question(1
                , "While working as a security guard, what authority must be given priority?"
                , "Federal and Provencal Law"
                , "Trespass to Property Act"
                , "The security company policy and guidelines"
                , "The client’s policy and procedures"
                , "Federal and Provencal Law"
                ,1
                , 0,3, 0,0));

        qList.add(new Question(1
                , "What is the difference between covert security surveillance and overt security surveillance?"
                , "Covert surveillance is done with a vehicle, overt surveillance is done outside."
                , "Covert surveillance is done at night and overt surveillance is done during the day"
                , "Covert surveillance is where the security guard is an obvious presence in uniform while surveillance is performed when the security guard isn’t immediately visible to the subject or public."
                , "Covert surveillance is where the security guard isn’t immediately visible to the subject or the public while overt surveillance is where the security guard is an obvious presence in uniform."
                , "Covert surveillance is where the security guard isn’t immediately visible to the subject or the public while overt surveillance is where the security guard is an obvious presence in uniform."
                ,1
                , 0,3, 0,0));

        qList.add(new Question(1
                , "When referring to a collective bargaining dispute or a strike that is occurring on your property, what would be the best example of an injunction?"
                , "Separating two employees that are arguing."
                , "Coordinating pedestrian traffic so no one gets hurt."
                , "Ensuring picketing workers so not obstruct other worker’s from entering the site by making sure they stay back the required as made mandatory by court order."
                , "An authorization to arrest all parties who enter the site."
                , "Ensuring picketing workers so not obstruct other worker’s from entering the site by making sure they stay back the required as made mandatory by court order."
                ,1
                , 0,3, 0,0));

        return qList;






    }
}
