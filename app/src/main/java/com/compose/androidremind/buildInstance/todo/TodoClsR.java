package com.compose.androidremind.buildInstance.todo;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Nullable;

public record TodoClsR(
        @NonNull String id, @NonNull String name, @NonNull Boolean f
) {
    TodoClsR copy(String id,String name,Boolean f){
        return new TodoClsR(
                id != null ? id : this.id,
                name != null ? name : this.name,
                f != null ? f : this.f
        );
    }

    @NonNull
    public TodoClsR copy(@NonNull Boolean ff) {
        return new TodoClsR(id,name,ff);
    }
}
