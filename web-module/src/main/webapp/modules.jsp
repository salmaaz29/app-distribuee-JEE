<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Gestion des Modules</title>
    <style>
        body { font-family: Arial; margin: 40px; background: #f9f9f9; }
        h1, h2 { color: #2c3e50; }
        form { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); margin-bottom: 20px; }
        input, button { padding: 10px; margin: 5px; border: 1px solid #ddd; border-radius: 4px; }
        button { background: #27ae60; color: white; cursor: pointer; }
        button:hover { background: #1e8449; }
        .btn-edit { background: #f39c12; }
        .btn-edit:hover { background: #e67e22; }
        .btn-delete { background: #e74c3c; }
        .btn-delete:hover { background: #c0392b; }
        .btn-cancel { background: #95a5a6; }
        .btn-cancel:hover { background: #7f8c8d; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; background: white; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        th, td { padding: 12px; text-align: left; border-bottom: 1px solid #eee; }
        th { background: #16a085; color: white; }
        tr:hover { background: #f5f5f5; }
        .actions { white-space: nowrap; }
        .edit-form { background: #fff3cd; border: 1px solid #ffeaa7; }
        .message { padding: 15px; margin: 20px 0; border-radius: 4px; }
        .error { background: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }
        .success { background: #d4edda; color: #155724; border: 1px solid #c3e6cb; }
    </style>
</head>
<body>
<h1>Gestion des Modules</h1>

<!-- Messages d'erreur et de succès -->
<c:if test="${not empty error}">
    <div class="message error">
        ❌ ${error}
    </div>
</c:if>

<c:if test="${not empty success}">
    <div class="message success">
        ✅ ${success}
    </div>
</c:if>

<!-- Formulaire d'ajout/modification -->
<div class="${not empty moduleEdit ? 'edit-form' : ''}">
    <h2>
        <c:choose>
            <c:when test="${not empty moduleEdit}">
                ✏️ Modifier le Module (ID: ${moduleEdit.idmodule})
            </c:when>
            <c:otherwise>
                ➕ Ajouter un module
            </c:otherwise>
        </c:choose>
    </h2>

    <form action="modules" method="post">
        <c:if test="${not empty moduleEdit}">
            <input type="hidden" name="id" value="${moduleEdit.idmodule}">
            <input type="hidden" name="action" value="update">
        </c:if>
        <c:if test="${empty moduleEdit}">
            <input type="hidden" name="action" value="add">
        </c:if>

        <input placeholder="Code" name="code" value="${moduleEdit.codemodule}" required>
        <input placeholder="Nom du module" name="nom" value="${moduleEdit.nommodule}" required>

        <button type="submit">
            <c:choose>
                <c:when test="${not empty moduleEdit}">💾 Modifier</c:when>
                <c:otherwise>✅ Ajouter</c:otherwise>
            </c:choose>
        </button>

        <c:if test="${not empty moduleEdit}">
            <a href="modules" class="btn-cancel" style="padding: 10px; text-decoration: none; border-radius: 4px;">❌ Annuler</a>
        </c:if>
    </form>
</div>

<!-- Liste des modules -->
<h2>📋 Liste des modules</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Code</th>
        <th>Nom</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${modules}" var="m">
        <tr>
            <td>${m.idmodule}</td>
            <td>${m.codemodule}</td>
            <td>${m.nommodule}</td>
            <td class="actions">
                <!-- Lien pour modifier -->
                <a href="modules?action=edit&id=${m.idmodule}"
                   style="background: #f39c12; color: white; padding: 5px 10px; text-decoration: none; border-radius: 3px; margin-right: 5px;">
                    ✏️ Modifier
                </a>

                <!-- Formulaire pour supprimer -->
                <form action="modules" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${m.idmodule}">
                    <button type="submit" class="btn-delete"
                            onclick="return confirm('Êtes-vous sûr de vouloir supprimer le module ${m.nommodule} ?')"
                            style="padding: 5px 10px; font-size: 12px;">
                        🗑️ Supprimer
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<!-- Message si aucun module -->
<c:if test="${empty modules}">
    <p style="text-align: center; color: #7f8c8d; margin-top: 20px;">
        Aucun module trouvé. Ajoutez le premier module !
    </p>
</c:if>
</body>
</html>