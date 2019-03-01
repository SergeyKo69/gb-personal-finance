package ru.gd.dev.spring.pfs.ui.view.content;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.MessageSource;
import ru.gd.dev.spring.pfs.ui.dto.AccountDto;
import ru.gd.dev.spring.pfs.ui.dto.AccountType;
import ru.gd.dev.spring.pfs.ui.view.menu.MenuView;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @autor Eremin Artem on 26.02.2019.
 */

@UIScope
@SpringComponent
@Route(value = "createAccount", layout = MenuView.class)
public class CreateAccountView extends VerticalLayout {

    @NotNull
    private MessageSource messageSource;

    @NotNull
    private Label title;

    @NotNull
    private TextField accountName;

    @NotNull
    private TextField accountAmount;

    @NotNull
    private ComboBox<AccountType> accountType;

    @Nullable
    private AccountDto accountDto;

    public CreateAccountView(@NotNull final MessageSource messageSource) {
        accountDto = new AccountDto();
        this.messageSource = messageSource;
        addTitle(messageSource);
        addNameField(messageSource);
        addAmountField(messageSource);
        addAccountType(messageSource);
        addCommentArea(messageSource);
        addSubmitButton(messageSource);
    }

    private void addTitle(@NotNull final MessageSource messageSource) {
        title = new Label(messageSource.getMessage("addaccount.title", null, getLocale()));
        title.getClassNames().add("pageTitle");
        add(title);
    }

    private void addNameField(@NotNull final MessageSource messageSource) {
        accountName = new TextField(messageSource.getMessage("addaccount.name", null, getLocale()));
        title.getClassNames().add("textField");
        title.getClassNames().add("accountName");
        add(accountName);
    }

    private void addAmountField(@NotNull final MessageSource messageSource) {
        accountAmount = new TextField(messageSource.getMessage("addaccount.amount", null, getLocale()));
        title.getClassNames().add("textField");
        title.getClassNames().add("accountAmount");
        add(accountAmount);
    }

    private void addAccountType(@NotNull final MessageSource messageSource) {
        accountType = new ComboBox<>(
                messageSource.getMessage("addaccount.choosetype",
                        null,
                        getLocale()));
        final Collection<AccountType> items = Arrays.stream(AccountType.values()).collect(Collectors.toList());
        accountType.setItems(items);
        accountType.addValueChangeListener(e -> accountDto.setType(e.getValue()));
        accountType.getClassNames().add("accountTypeComboBox");
        add(accountType);
    }

    private void addCommentArea(final MessageSource messageSource) {
        final TextArea commentArea = new TextArea(messageSource.getMessage("comment", null, getLocale()));
        commentArea.getClassNames().add("textArea");
        commentArea.getClassNames().add("commentArea");
        add(commentArea);
    }

    private void addSubmitButton(@NotNull final MessageSource messageSource) {
        final Button createButton = new Button(messageSource.getMessage("button.create", null, getLocale()));
        createButton.addClickListener(e -> {
            createAccountDTO();
            createButton.getUI().ifPresent(ui -> ui.navigate("accounts"));
        });
        createButton.getClassNames().add("button");
        createButton.getClassNames().add("createAccountButton");
        add(createButton);
    }

    private AccountDto createAccountDTO() {
        accountDto.setId(UUID.randomUUID().toString());
        accountDto.setName(accountName.getValue());
        accountDto.setAmount(accountAmount.getValue());
        accountDto.setActive(true);
        System.out.println(accountDto);
        return accountDto;
    }
}
