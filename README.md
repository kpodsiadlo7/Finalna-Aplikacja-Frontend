# FRONTEND APPLICATION INFORMATION

#### Poniżej znajdują się technologie użyte w aplikacji
* RestAPI 
* Spring boot
* Spring web
* NBP currency API
* Weather API
* Enums
* Vaadin
* Lombok
* Endpoint config
* Core configuration

W aplikacji możemy zarejestrować się jako pacjent, uprzednio zaznaczając do której kliniki
chcemy się zapisać. Możemy wybrać płatność w formie gotówki lub kartą z konkretną walutą, 
dzięki połączeniu aplikacji z zewnętrzną API od narodowego banku polskiego, mamy aktualny 
kurs walut dla EUR oraz USD. 
####
Mamy możliwość oceny kliniki z perspektywy pacjetna, musimy tylko wskazać konkretną klinikę.
####
Dodatkowo możemy jako personel aktualizować historie choroby konkretnemu pacjentowi.
####
Każda zmiana jest aktualizowana w czasie rzeczywistym i po dodaniu oceny,historii choroby
lub zarejestrowaniu się, widzimy odrazu zmiany w listach, nic się w nich nie gubi ponieważ, każda lista
wyświetla id konkretnego pacjenta lub kliniki.
####
Dodatkowo jak na profesjonalną klinikę przystało, aplikacja połączona jest z kolejną zewnętrzną
API, tym razem związana z pogodą, dzięki czemu możemy sprawdzić aktualną temperaturę wpisując 
lokalizacje.


Poniżej znajduje się link do backendu aplikacji, która odpowiada za całą logikę
####
https://github.com/kpodsiadlo7/Finalna-Aplikacja-Klinika-Backend
