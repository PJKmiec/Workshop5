$(document).ready(function () {

    // pokazywanie / chowanie formularza dodawania książki
    $('a.add').click(function () {
        $(this).hide();
        $('.newbook').show();
        return false;
    });

    $('#addBookForm').submit(function (e) {
        e.preventDefault();
    }) // blokada wysłania formularza

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

    // dodawanie nowej książki
    $('#addBookSubmit').click(function () {
        var validated = true;

        $('.newbook input[type=text]').each(function () {
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



});