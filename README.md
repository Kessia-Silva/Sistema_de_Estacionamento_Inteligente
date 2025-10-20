# Sistema_de_Estacionamento_Inteligente

Sistema de Estacionamento Inteligente com Controle de Acesso 
A Estacione na B Estacionamentos Ltda. administra o pátio de sua unidade central. Por 
norma interna alinhada à lei municipal de priorização (idosos, PCD, gestantes), o 
estacionamento possui 7 vagas, sendo 5 vagas NORMAIS e 2 vagas PRIORITÁRIAS 
(sinalizadas e reservadas). Auditorias periódicas exigem comprovantes de acesso (logs) 
e indicadores de recusa por falta de vagas. 
Nos horários de pico (7h–10h e 16h–19h), a chegada de veículos é intensa e imprevisível. 
Para garantir tempo de resposta e segurança de concorrência, a Estacione na B decidiu 
implementar um software de controle do estacionamento em Java, utilizando 
processamento concorrente via thread pool (Executor) e controle de capacidade via 
Semaphore. 
Você foi contratado pela empresa para implementar um sistema que gerencia um 
estacionamento com vagas limitadas e controla o fluxo de veículos entrando e saindo. 
Os veículos devem ser categorizados em NORMAL ou PRIORITARIO. O estacionamento 
deve garantir que veículos prioritários tenham preferência no alocamento das vagas. 
Além disso, o sistema deve registrar o log de entrada e saída de veículos, e um contador 
de veículos que não conseguiram estacionar por falta de vaga. 