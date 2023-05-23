<!DOCTYPE html>
<html lang="en">
<head>
     <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <meta http-equiv="X-UA-Compatible" content="ie=edge">
     <title>Emploi</title>
    <style>
      table {

        border-collapse: collapse;
        width: 100%;
        padding: 15px;
    }
    table th{
        font-size: 8px !important;
        text-align: center !important;
    }
    table th,
    table td {
        border: 1px solid black;
        padding: 20px;
        text-align: center;
    }
    div{
        padding-right: 15px;
    }
    *{
        font-size: 12px;
    }
    .vide{
        text-align: center
    }
    .container{
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
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
    .class{
        margin-left: 5px;
        font-weight: 500;
    }
    .footer{
            text-align: center;
        }
    .matiere_salle{
        font-size: 8px;
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
            de la Formation de la Société: <span class="class">Emploi du classe : {{$classe->nom}}</span>
         </p>
    </div>
    <div class="container">
        <div class="table">
            <table>
                <thead>
                    <tr>
                        <th>           </th>
                        <th>8h30-10h00 </th>
                        <th>10h10-11h40</th>
                        <th>11h50-13h20</th>
                        <th>12h40-14h20</th>
                        <th>14h20-15h50</th>
                        <th>16h00-17h30</th>
                    </tr>
                </thead>
                <tbody>
                    @foreach ($data as $jour => $seances)
                        <tr>
                            <td>{{ $jour }}</td>
                            @foreach ($seances as $numero => $seance)
                                    @if($seance!==null)
                                        <td>
                                        @foreach ($seance as $item)
                                            <div>
                                                {{ $item->user->FirstName }}<br>
                                               <span class="matiere_salle"> {{ $item->salle->nom }} - {{ $item->matiere->nom }}</span>
                                            </div>
                                        @endforeach
                                        </td>
                                    @else
                                    <td class="vide">------</td>
                                    @endif
                            @endforeach
                        </tr>
                    @endforeach
                </tbody>
            </table>
        </div>
    </div>
    <div>
        <p class="footer">
           ISET Bizerte ,BP.n° 65 Campus universitaire 7035 Menzel Abderahmene <br>
           Tél:72.570.601 - Fax:72.572.455 <br>
        </p>
    </div>

</body>
</html>
