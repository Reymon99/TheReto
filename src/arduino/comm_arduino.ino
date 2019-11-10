const int  LED_Amarillo = 8;
const int LED_Azul = 9;
const int LED_Rojo = 10;
const int LED_Verde = 11;
const int LED_Blanco = 12;
int Byte_Entrada = 0;

void setup () {
  Serial.begin (9600);//apertura puerto
  pinMode(LED_Amarillo, OUTPUT);
  pinMode(LED_Azul, OUTPUT);
  pinMode(LED_Rojo, OUTPUT);
  pinMode(LED_Verde, OUTPUT);
  pinMode(LED_Blanco, OUTPUT);
  digitalWrite(LED_Amarillo, LOW);
  digitalWrite(LED_Azul, LOW);
  digitalWrite(LED_Rojo, LOW);
  digitalWrite(LED_Verde, LOW);
  digitalWrite(LED_Blanco, LOW);

}
void loop() {
  if (Serial.available () > 0) { //puerto disponible
    Byte_Entrada = Serial.read(); //verifica lo que viene de jawa
    if (Byte_Entrada == '0') {
      digitalWrite(LED_Amarillo, LOW); //amatillo off
    } else if (Byte_Entrada == '1') {
      digitalWrite(LED_Amarillo , HIGH); //amarillo on
    } else if (Byte_Entrada == '2') {
      digitalWrite(LED_Azul , LOW); //azul off
    } else if (Byte_Entrada == '3') {
      digitalWrite(LED_Azul , HIGH); //azul on
    } else if (Byte_Entrada == '4') {
      digitalWrite(LED_Rojo , LOW); //rojo off
    } else if (Byte_Entrada == '5') {
      digitalWrite(LED_Rojo , HIGH); //rojo on
    } else if (Byte_Entrada == '6') {
      digitalWrite(LED_Verde , LOW); //verde off
    } else if (Byte_Entrada == '7') {
      digitalWrite(LED_Verde , HIGH); // verde on
    } else if (Byte_Entrada == '8') {
      digitalWrite(LED_Blanco , LOW); //blanco off
    } else if (Byte_Entrada == '9') {
      digitalWrite(LED_Blanco , HIGH); //blanco on
    }
  }
}







