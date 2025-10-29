<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Gestion Scolaire - Accueil</title>
  <style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      color: #333;
      min-height: 100vh;
      display: flex;
      align-items: center;
      justify-content: center;
    }
    .container {
      background: white;
      padding: 40px;
      border-radius: 16px;
      box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
      text-align: center;
      max-width: 600px;
      width: 90%;
    }
    h1 {
      font-size: 2.5rem;
      margin-bottom: 10px;
      color: #2c3e50;
    }
    p {
      font-size: 1.1rem;
      margin-bottom: 30px;
      color: #7f8c8d;
    }
    .menu {
      display: flex;
      flex-direction: column;
      gap: 15px;
    }
    .btn {
      display: block;
      padding: 15px 20px;
      font-size: 1.1rem;
      text-decoration: none;
      color: white;
      border-radius: 8px;
      transition: all 0.3s ease;
      box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
    }
    .btn:hover {
      transform: translateY(-3px);
      box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
    }
    .btn-etudiants { background: #3498db; }
    .btn-modules   { background: #27ae60; }
    .btn-notes     { background: #e74c3c; }

    .btn-etudiants:hover { background: #2980b9; }
    .btn-modules:hover   { background: #1e8449; }
    .btn-notes:hover     { background: #c0392b; }

    footer {
      margin-top: 40px;
      font-size: 0.9rem;
      color: #95a5a6;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Gestion Scolaire</h1>
  <p>Bienvenue dans l'application de gestion des étudiants, modules et notes.</p>

  <div class="menu">
    <a href="etudiants" class="btn btn-etudiants">
      Gestion des Étudiants
    </a>
    <a href="modules" class="btn btn-modules">
      Gestion des Modules
    </a>
    <a href="suivies" class="btn btn-notes">
      Gestion des Notes
    </a>
  </div>

  <footer>
    &copy; 2025 FSTT - Application JEE Distribuée
  </footer>
</div>
</body>
</html>