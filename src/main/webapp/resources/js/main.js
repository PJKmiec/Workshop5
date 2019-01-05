$(document).ready(function () {

    // pokazywanie / chowanie formularza dodawania książki
    $('a.add').click(function () {
        $(this).hide();
        $('.newbook').show();
        return false;
    });

    // blokada wysłania formularzy
    $('body').on('submit', '#addBookForm, #editBookForm', function(e){
        e.preventDefault();
    })

    // wybielanie zaczerwienionych pól formularza na blurze
    $('input[type=text]').blur(function () {
        if ($(this).val().length > 1) {
            $(this).css('background-color', 'white');
        }
    });

    // przycisk Anuluj w formularzu dodawania książki
    $('#addBookCancel').click(function () {
        $('.newbook input[type=text]').val('').css('background-color', 'white');
        $('.newbook').hide();
        $('a.add').show();
    });

    // przycisk Anuluj w formularzu edycji książki
    $('td').on('click', '#editBookCancel', function(e){
        $('.editform').remove();
    });

    // dodawanie nowej książki
    $('#addBookSubmit').click(function () {
        var validated = true;

        $('.newbook input[type="text"]').each(function () {
            if ($(this).val().length < 1) {
                $(this).css('background-color', 'red');
                validated = false;
            }
        });

        if (validated) {
            $.ajax({
                url: "/books/add-book",
                type: "POST",
                data: $('#addBookForm').serialize()
            }).done(function () {
                location.reload();
            }).fail(function (xhr, status, err) {
                alert(status + ":" + err);
            });
        }
    });

    // podgląd szczegółów książki
    $('a.view').click(function () {

        if ($('.details')[0]) {
            $('.details').remove();
        } else {
            $('.details').remove();

            var url = $(this).attr("href");
            var handler = $(this);

            $.get(url, function () {
            }).done(function (book) {
                handler.append('<div class="details"><h2>' + book.title + '</h2><br>' +
                    '<b>ID:</b> ' + book.id + '<br>' +
                    '<b>Autor:</b> ' + book.author + '<br>' +
                    '<b>ISBN:</b> ' + book.isbn + '<br>' +
                    '<b>Wydawca:</b> ' + book.publisher + '<br>' +
                    '<b>Rodzaj:</b> ' + book.type +
                    '</div>');
            }).fail(function (xhr, status, err) {
                alert(status + ":" + err);
            });
        }
        return false;
    });

    // kasowanie książki
    $('a.delete').click(function () {
        var url = $(this).attr("href");

        $.ajax({
            url: url,
            type: "DELETE"
        }).done(function () {
            location.reload();
        }).fail(function (xhr, status, err) {
            alert(status + ":" + err);
        });

        return false;
    });

    // otwieranie edycji książki
    $('a.edit').click(function () {

        if ($('.editform')[0]) {
            $('.editform').remove();
        } else {
            $('.editform').remove();

            var url = $(this).attr("href").replace('edit', 'view');
            var handler = $(this).parent();

            $.get(url, function () {
            }).done(function (book) {
                handler.append('<div class="editform"><form id="editBookForm" method="post">' +
                    '<input type="hidden" name="id" value="' + book.id + '">' +
                    'Tytuł: <input type="text" name="title" value="' + book.title + '"><br>' +
                    'Autor: <input type="text" name="author" value="' + book.author + '"><br>' +
                    'Rodzaj: <input type="text" name="type" value="' + book.type + '"><br>' +
                    'ISBN: <input type="text" name="isbn" value="' + book.isbn + '"><br>' +
                    'Wydawca: <input type="text" name="publisher" value="' + book.publisher + '"><br><br>' +
                    '<button id="editBookSubmit">Zapisz zmiany &raquo;</button> <button id="editBookCancel">Anuluj</button>' +
                    '</div>');
            }).fail(function (xhr, status, err) {
                alert(status + ":" + err);
            });
        }
        return false;
    });

    // zapisywanie edycji książki
    $('td').on('click', '#editBookSubmit', function(e){
        var validated = true;

        $('.editform input[type="text"]').each(function () {
            if ($(this).val().length < 1) {
                $(this).css('background-color', 'red');
                validated = false;
            }
        });

        if (validated) {
            $.ajax({
                url: "/books/book-edit/" + $('.editform input[name=id]').val(),
                type: "PUT",
                data: $('#editBookForm').serialize()
            }).done(function () {
                location.reload();
            }).fail(function (xhr, status, err) {
                alert(status + ":" + err);
            });
        }

    });

    // zaznaczanie całego tekstu w inpucie przy edycji
    $('td').on('focus', 'input', function () {
        $(this)
            .one('mouseup', function () {
                $(this).select();
                return false;
            })
            .select();
    });



});