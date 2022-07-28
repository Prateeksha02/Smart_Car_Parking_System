#include "FirebaseESP8266.h"  // Install Firebase ESP8266 library
#include <ESP8266WiFi.h>
#include <FirebaseESP8266.h>
#include<ArduinoJson.h>

#include <NTPClient.h>
#include <WiFiUdp.h>
WiFiUDP ntpUDP;
NTPClient timeClient(ntpUDP, "pool.ntp.org");
 
#include <Servo.h>                         

#define FIREBASE_HOST "smart-parking-db7d9-default-rtdb.firebaseio.com"                     //Your Firebase Project URL goes here without "http:" , "\" and "/"
#define FIREBASE_AUTH "PeJroxuWr8FAz5pEcpLcgGww3AV5U4fvlbe0rKb8" //Your Firebase Database Secret goes here

#define WIFI_SSID "Shenoy"                                               //WiFi SSID to which you want NodeMCU to connect
#define WIFI_PASSWORD "DontAskMe$"        
String fireAvailable = "";
//FirebaseData fireAvailable;
Servo servo;

int inputPin = 13; // choose input pin (for Infrared sensor) 
int slot1 = 12;
int slot2 = 14;// choose input pin (for Infrared sensor) 

int count = 0;
String card_no(12);
String zero="0";
// Declare the Firebase Data object in the global scope
FirebaseData firebaseData;

// Declare global variable to store value
//int val=0;
//int LED1= 5;
//int LED2= 4;


void setup() {

  Serial.begin(9600);                                   // Select the same baud rate if you want to see the datas on Serial Monitor

  Serial.println("Serial communication started\n\n");  
           
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);                                     //try to connect with wifi
  Serial.print("Connecting to ");
  Serial.print(WIFI_SSID);

  //digitalWrite(LED1,LOW);
  //digitalWrite(LED2,LOW);
  
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }

  
  Serial.println();
  Serial.print("Connected to ");
  Serial.println(WIFI_SSID);
  Serial.print("IP Address is : ");
  Serial.println(WiFi.localIP());                                            //print local IP address
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);   // connect to firebase

  Firebase.reconnectWiFi(true);
  delay(1000);

 pinMode(inputPin, INPUT); // declare Infrared sensor as input

   servo.attach(D4); //D4
   servo.write(0);
   delay(2000);

  pinMode(5, OUTPUT);
  digitalWrite(5, LOW);
  pinMode(4, OUTPUT);
  digitalWrite(4, LOW);
  
  // Initialize a NTPClient to get time
  timeClient.begin();
  // Set offset time in seconds to adjust for your timezone, for example:
  timeClient.setTimeOffset(19800);
 }

void loop() { 
  timeClient.update();

  read:
  while(digitalRead(inputPin)== LOW && Serial.available())
   {    
          Serial.println("Detected");
          count = 0;
          while(Serial.available())
          {
             card_no = Serial.readString();
          }
          Serial.println(card_no);

          servo.write(180);
          delay(3000);
          servo.write(0);
          delay(3000);
        while( true )
        {
         if(digitalRead(slot1)== LOW)
           {
              Serial.println("Slot1 is occupied");
              digitalWrite(5,HIGH);
              Firebase.setString(firebaseData,"/Banashankari/Slots/Slot1",card_no);
              
              Firebase.setString(firebaseData,"/Charges/Timing/Slot1/Time_in",timeClient.getFormattedTime());
           
              goto next;
           }
          if(digitalRead(slot2)== LOW)
           {
              Serial.println("Slot2 is occupied");
              digitalWrite(4,HIGH);
              Firebase.setString(firebaseData,"/Banashankari/Slots/Slot2",card_no);
              
              Firebase.setString(firebaseData,"/Charges/Timing/Slot2/Time_in",timeClient.getFormattedTime());
              
              goto next1;
           }
        }
        while(1)
        {       next:
                if(digitalRead(slot1)==HIGH)
                {
                 Serial.println("Slot1 is vacant");
                 digitalWrite(5,LOW);
                 Firebase.setString(firebaseData,"/Banashankari/Slots/Slot1",zero);
                 
                 Firebase.setString(firebaseData,"/Charges/Timing/Slot1/Time_out",timeClient.getFormattedTime());
               
                 goto next2;
                } 
                else
                {
                  goto next2;
                }
                next1:
                if(digitalRead(slot2)==HIGH)
                {
                  Serial.println("Slot2 is vacant");
                  digitalWrite(4,LOW);
                 Firebase.setString(firebaseData,"/Banashankari/Slots/Slot2",zero);
                 
                 Firebase.setString(firebaseData,"/Charges/Timing/Slot2/Time_out",timeClient.getFormattedTime());
  
                 goto next2;
                }
                else
                {
                  goto next2;
                }
                goto next2;
        }
          next2: Serial.println("Bye"); 
          
          servo.write(180);
          delay(3000);
          servo.write(0);
          delay(3000);    
            
    }
        
        goto read;

}

// Firebase Error Handling And Writing Data At Specifed Path************************************************


/*if (Firebase.setInt(firebaseData, "/data", val)) {    // On successful Write operation, function returns 1  
               Serial.println("Value Uploaded Successfully");
               Serial.print("Val = ");
               Serial.println(val);
               Serial.println("\n");
               
               val++;
               delay(1000);

     }

else {        
    Serial.println(firebaseData.errorReason());
}*/


/* NOTE:
 *  To upload value, command is ===> Firebase.setInt(firebaseData, "path", variable);
 *  Example                     ===>  Firebase.setInt(firebaseData, "/data", val);
 */
       
