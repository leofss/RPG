# RPG
API Rest em java spring boot com sistema de turnos e cadastro de personagens que simula um sistema de batalha utilizando PostgreSql 

# SETUP
- Realize a instalação dos pacotes necessários
- Configure o seu banco Postgresql
- Mude o arquivo application.properties de acordo com suas configurações de banco

# ENDPOINTS

## PERSONAGEM

  ### Criação 
  
   /character
   
   #### POST REQUEST BODY
      // Character type deve ser HERO ou MONSTER
        {
            "character_type": "HERO",
            "name":"leo",
            "health_points":2,
            "strength": 2,
            "defense":2,
            "agility":2,
            "dice_times_roll":0,
            "dice_faces":8
        }

  #### RESPONSE
      **STATUS 201**
          {
            "character_type": "HERO",
            "name":"leo",
            "health_points":2,
            "strength": 2,
            "defense":2,
            "agility":2,
            "dice_times_roll":0,
            "dice_faces":8
          }
        
   ### Pegar todos  
  
   /character
   
   #### GET REQUEST 

   #### RESPONSE
        **STATUS 200**
          [
              {
                  "id": 553,
                  "character_type": "HERO",
                  "name": "Leonardo",
                  "health_points": 20,
                  "strength": 20,
                  "defense": 40,
                  "agility": 30,
                  "dice_times_roll": 8,
                  "dice_faces": 1
              },
              {
                  "id": 602,
                  "character_type": "MONSTER",
                  "name": "Ogro",
                  "health_points": 10,
                  "strength": 10,
                  "defense": 30,
                  "agility": 20,
                  "dice_times_roll": 8,
                  "dice_faces": 1
              },
          ]
          
  ### Pegar por Id  
  
   /character/Id
   
  #### GET REQUEST 

  #### RESPONSE
        **STATUS 200**
        {
          "id": 702,
          "character_type": "MONSTER",
          "name": "Ogro",
          "health_points": 10,
          "strength": 10,
          "defense": 30,
          "agility": 20,
          "dice_times_roll": 1,
          "dice_faces": 8
        }

  ### Editar por Id  
  
   /character/Id
   
   #### PUT REQUEST BODY
     {
        "character_type": "HERO",
        "name":"Mudando nome",
        "health_points": 5,
        "strength": 30,
        "defense": 30,
        "agility": 15,
        "dice_times_roll": 1,
        "dice_faces": 20
     }

  #### RESPONSE
        **STATUS 200**
     {
        "id": 553,
        "character_type": "HERO",
        "name":"Mudando nome",
        "health_points": 5,
        "strength": 30,
        "defense": 30,
        "agility": 15,
        "dice_times_roll": 1,
        "dice_faces": 20
     }
     
  ### Deletar por Id  
  
   /character/Id
   
  #### DELETE REQUEST 

  #### RESPONSE 204

## SESSÃO
  
### Criação 
  
   /session
   
   #### POST REQUEST BODY
   
          {
              "ally_id":4203,
              "enemy_id":902
          }
          
  #### RESPONSE
      **STATUS 201**
          {
              "session_id": 2302,
              "ally_roll": 5,
              "enemy_roll": 12
          }

### Jogar turno 
  
   /session/turn
   
   #### POST REQUEST BODY
   
          {
              "session_id": 2302,
              "attacker_id":902,
              "defender_id":4203
          }
          
  #### RESPONSE
  
      **STATUS 201**
          //Ataque abateu defensor
          {
              "damage_done": 28,
              "current_defender_health_points": -26,
              "is_session_over": true,
              "message": "Defender was killed!"
          }

          ou
          
          //Caso o defensor nessa sessão tenha já morrido 
          {
              "is_session_over": true,
              "message": "Session is over!"
          }

          ou

          //Caso o defensor tenha uma defesa maior que o ataque
          {
              "is_session_over": false,
              "message": "Attack failed! Defender defended successfully."
          }

          ou

          //Ataque seja maior que a defesa do defensor
          {
              "damage_done": 28,
              "current_defender_health_points": 10,
              "is_session_over": false,
              "message": "Successful attack"
          }
  
### Pegar histórico de sessão por Id

 /session/Id
   
  #### GET REQUEST 

  #### RESPONSE 200
    [
        {
            "session": {
                "id": 2204,
                "currentAllyHealthPoints": -29,
                "currentEnemyHealthPoints": 60,
                "allyRollNumber": 12,
                "enemyRollNumber": 3,
                "currentTurn": "ALLY",
                "characterAlly": {
                    "id": 4203,
                    "characterType": "HERO",
                    "name": "leo",
                    "healthPoints": 2,
                    "strength": 2,
                    "defense": 2,
                    "agility": 2,
                    "diceTimesRoll": 2,
                    "diceFaces": 8
                },
                "characterEnemy": {
                    "id": 902,
                    "characterType": "MONSTER",
                    "name": "NormalMonster",
                    "healthPoints": 60,
                    "strength": 20,
                    "defense": 20,
                    "agility": 5,
                    "diceTimesRoll": 2,
                    "diceFaces": 8
                },
                "turnCount": 2,
                "sessionOver": true
            },
            "first_to_attack": "ALLY",
            "attack": 9,
            "current_turn_count": 1,
            "defense": 30,
            "damage_done": 13,
            "current_ally_hp": 2,
            "current_enemy_hp": 60
        },
        {
            "session": {
                "id": 2204,
                "currentAllyHealthPoints": -29,
                "currentEnemyHealthPoints": 60,
                "allyRollNumber": 12,
                "enemyRollNumber": 3,
                "currentTurn": "ALLY",
                "characterAlly": {
                    "id": 4203,
                    "characterType": "HERO",
                    "name": "leo",
                    "healthPoints": 2,
                    "strength": 2,
                    "defense": 2,
                    "agility": 2,
                    "diceTimesRoll": 2,
                    "diceFaces": 8
                },
                "characterEnemy": {
                    "id": 902,
                    "characterType": "MONSTER",
                    "name": "NormalMonster",
                    "healthPoints": 60,
                    "strength": 20,
                    "defense": 20,
                    "agility": 5,
                    "diceTimesRoll": 2,
                    "diceFaces": 8
                },
                "turnCount": 2,
                "sessionOver": true
            },
            "first_to_attack": "ALLY",
            "attack": 27,
            "current_turn_count": 2,
            "defense": 10,
            "damage_done": 31,
            "current_ally_hp": -29,
            "current_enemy_hp": 60
        }
    ]

## Cálculo

  Esses endpoints são chamados automaticamente pela aplicação para realização da jogada de turno

  ### Calcular defesa por Id  
  
   /calculate/defense/id
   
  #### GET REQUEST 

  #### RESPONSE 
      **STATUS 200**
      {
          "result": 57
      }
      
  ### Calcular ataque por Id  
  
   /calculate/attack/id
   
  #### GET REQUEST 

  #### RESPONSE 
      **STATUS 200**
      {
          "result": 57
      }
      
  ### Calcular dano por Id  
  
   /calculate/damage/id
   
  #### GET REQUEST 

  #### RESPONSE 
      **STATUS 200**
      {
          "result": 57
      }
  
