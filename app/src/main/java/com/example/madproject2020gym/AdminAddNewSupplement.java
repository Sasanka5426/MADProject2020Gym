package com.example.madproject2020gym;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class AdminAddNewSupplement extends AppCompatActivity {

    private String Description,Price,Supname,savecurrentDate,savecurrentTime;
    private Button AddNewSupplementButton;
    private ImageView InputSupplementImage;
    private EditText InputSupplementName, InputSupplementPrice,InputSupplementDescription;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String supplementRandomKey,downloadImageUrl;
    private StorageReference SupplementImageRef;
    private DatabaseReference SupplementRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_supplement);
        SupplementImageRef = FirebaseStorage.getInstance().getReference().child("Supplement Images");
        SupplementRef= FirebaseDatabase.getInstance().getReference().child("Supplement");

        AddNewSupplementButton = (Button) findViewById(R.id.add_new_supplement);
        InputSupplementImage= (ImageView) findViewById(R.id.select_image);
        InputSupplementName= (EditText) findViewById(R.id.supplement_name);
        InputSupplementPrice= (EditText) findViewById(R.id.supplement_price);
        InputSupplementDescription=(EditText) findViewById(R.id.supplement_description);

        InputSupplementImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                OpenGallery();
            }
        });

        AddNewSupplementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ValidateSupplementData();
            }
        });
    }



    private void OpenGallery()
    {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GalleryPick && requestCode==RESULT_OK && data!=null)
        {
            ImageUri = data.getData();
            InputSupplementImage.setImageURI(ImageUri);
        }
    }

    private void ValidateSupplementData()
    {
        Description = InputSupplementDescription.getText().toString();
        Price = InputSupplementPrice.getText().toString();
        Supname = InputSupplementName.getText().toString();

        if(ImageUri == null)
        {
            Toast.makeText(this,"Supplement image is mandatory...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Description))
        {
            Toast.makeText(this,"Please write supplement description...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Price))
        {
            Toast.makeText(this,"Please write supplement price...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Supname))
        {
            Toast.makeText(this,"Please write supplement name...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StoreSupplementInformation();
        }


    }

    private void StoreSupplementInformation()
    {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentData = new SimpleDateFormat("MMM dd,yyyy");
        savecurrentDate= currentData.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
        savecurrentDate= currentData.format(calendar.getTime());

        supplementRandomKey=savecurrentDate+savecurrentTime;

        final StorageReference filePath= SupplementImageRef.child(ImageUri.getLastPathSegment() + supplementRandomKey);

        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(AdminAddNewSupplement.this,"Error:"+message, Toast.LENGTH_SHORT).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                Toast.makeText(AdminAddNewSupplement.this,"Image uploaded Successfully", Toast.LENGTH_SHORT).show();

                Task<Uri> uriTask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful())
                        {
                            throw task.getException();
                        }
                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if(task .isSuccessful())
                        {
                            downloadImageUrl= task.getResult().toString();

                            Toast.makeText(AdminAddNewSupplement.this,"Successfully...", Toast.LENGTH_SHORT).show();

                            SaveSupplementInfoToDatabase();
                        }
                    }
                });
            }
        });
    }

    private void SaveSupplementInfoToDatabase()
    {
        HashMap<String,Object> supplementMap = new HashMap<>();
        supplementMap.put("supid",supplementRandomKey);
        supplementMap.put("date",savecurrentDate);
        supplementMap.put("time",savecurrentTime);
        supplementMap.put("description",Description);
        supplementMap.put("image",downloadImageUrl);
        supplementMap.put("price",Price);
        supplementMap.put("supname",Supname);


        SupplementRef.child(supplementRandomKey).updateChildren(supplementMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Intent intent =new Intent(AdminAddNewSupplement.this,AdminCategory.class);
                    startActivity(intent);
                    Toast.makeText(AdminAddNewSupplement.this,"Supplement is added successfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String message = task.getException().toString();
                    Toast.makeText(AdminAddNewSupplement.this, "Error: "+message, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}