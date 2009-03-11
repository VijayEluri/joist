package joist.web.pages.controls.form;

import joist.web.AbstractPage;
import joist.web.controls.form.Form;
import joist.web.controls.form.SubmitButton;
import joist.web.controls.form.TextField;

import org.exigencecorp.bindgen.Bindable;
import org.exigencecorp.util.TestCounter;

import bindgen.joist.web.pages.controls.form.TextFieldPageBinding;

@Bindable
public class TextFieldPage extends AbstractPage {

    public static TestCounter submitted = new TestCounter();
    public Form form = new Form("form");
    public String value = "foo";

    public void onInit() {
        TextFieldPageBinding b = new TextFieldPageBinding(this);
        this.form.add(new TextField(b.value()));
        this.form.add(new SubmitButton(b.submit()));
    }

    public void submit() {
        TextFieldPage.submitted.next();
    }

}