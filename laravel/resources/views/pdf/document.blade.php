<!DOCTYPE html>
<html lang="en">
<head>
     <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <meta http-equiv="X-UA-Compatible" content="ie=edge">
     <title>Document</title>
    <style>
        *{
            padding: 0;
            margin: 0;
            box-sizing: border-box;
            font-family: 'Times New Roman', Times, serif
        }
        .date{
           float: right;
           padding: 15px;
        }
        .container_date{
            margin-bottom: 35px;
        }
        .titre{
            text-align: center;
            position: relative;
            padding-top: 15px;
        }
        .objet{
            margin-top:30px;
            padding: 15px;
        }
        .paraghraphe{
            margin-top: 5px;
            text-align: justify;
            padding: 15px;
            line-height: 20px;
        }

        table{
            width: 100%;
            padding: 15px;
        }
        tr,td{
            line-height: 10px;
            padding: 5px;
        }
        td{
            border: 1px solid #000;
            width: 100%
        }
        .image{
            text-align: center;
            padding: 10px;
        }
        .footer{
            text-align: center;
        }

    </style>
</head>
<body>
    <div class="image">
        <img src="data:image/jpeg;base64,{{$bareIset}}'"/>
    </div>
    <div class="container_date">
        <p class="date">{{$date}}</p>
    </div>
    <div>
         <p class="titre">
                A L'attention du Directeur <br>
            de la Formation de la Société:...................
         </p>
    </div>
    <div>
       <p class="objet"><Strong>Objet: Demande de Stage Obligatoire</Strong> </p>
    </div>
    <div>
        <p class="paraghraphe">
         L'Institut Supérieur des Etudes Technologiques de Bizerte assure depuis un certain nombre d'années une formation en licences
         devant être consolidée par <b> un stage de perfectionnement </b>en entreprise et ce, dans le cadre de l'ouverture de notre institution
         universitaire sur son environnement socio-économique.
        </p>
    </div>
    <div>
        <p class="paraghraphe">
            Nous venons donc, par la présente, vous demander de bien vouloir accueillir, au sein de votre entreprise,{{$user['sex']=="Man" ? 'l\'étudiant': 'l\'étudiante'}}
             <b>{{strtoupper($user['LastName']).' '.strtoupper($user['FirstName'])}} </b>titulaire de la CIN n°<b>{{$user['Cin']}}</b> {{$user['sex']=="Man" ? 'inscrit': 'inscrite'}}
            au cours de l'année universitaire <b>2022/2023</b> en {{$niveau}} année de la <b>LICENCE APPLIQUEE EN TECHNOLOGIES DE L'INFORMATIQUE:{{$specaialite}}</b> Classe {{$classe_current}}
             Ce stage obligatoire est prévu pour une durée de quatre semaines, du <b>09 Janvier</b> au <b>04 Février 2023</b>.
         </p>
    </div>
    <div>
        <p class="paraghraphe">
            Nous vous signalons, que durant la période de stage, l'{{$user['sex']=="Man" ? 'étudiant': 'étudiante'}}  est {{$user['sex']=="Man" ? 'couvert': 'couverte'}}  par la Mutuelle Accident Scolaire et Universitaire-MASU(11051424-006).
         </p>
    </div>
    <div>
        <p class="paraghraphe">
            Nous vous prions, en cas de réponse positive, de bien vouloir remplir la fiche de stage ci-dessous et la remettre à l'{{$user['sex']=="Man" ? 'étudiant': 'étudiante'}} ou la retourner par fax, et ce afin de préparer la lettre d'affectation du stagiaire.
            En vous remerciant pour votre collaboration, nous vous adressons, Madame, Monsieur, l'expression de notre respect.
        </p>
    </div>
    <hr>
    <p class="titre"><b> Fiche Réponse</b></p>
    <table >
         <tr>
            <td colspan="2">Entreprise</td>
         </tr>
         <tr>
            <td colspan="2">Département :</td>
         </tr>
         <tr>
            <td colspan="2">Le responsable direct du stagiaire :</td>
         </tr>
         <tr>
            <td colspan="2">Fonction du responsable direct du stagiaire :</td>
         </tr>
         <tr>
            <td>Stage prévu du</td>
            <td>au</td>
         </tr>
         <tr>
            <td colspan="2">Adresse :</td>
         </tr>
         <tr>
            <td>Fax :</td>
            <td>Tél :</td>
         </tr>
         <tr >
            <td style="border: none ;">
                Signature et cachet du responsable de l'entreprise
            </td>
            <td style="border: none;">
                Signature et cachet du responsable ISET
            </td>
         </tr>
         <tr>
            <td  style="border: none"></td>
            <td  style="border: none"><img src="data:image/jpeg;base64,{{$base64}}'"/></td>
         </tr>
         <tr>
    </table>
    <div>
        <p class="footer">
           ISET Bizerte ,BP.n° 65 Campus universitaire 7035 Menzel Abderahmene <br>
           Tél:72.570.601 - Fax:72.572.455 <br>
           Email:stages@isetbz.rnu.tn
        </p>
    </div>
</body>
</html>
