**Lösung VolumeFilter**

```
public int[] smoothValues(int[] values) {
        int x;
        int[] result = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            if (i == 0) {
                x = values[i] + values[i + 1];
                result[i] = x / 2;
            } else if (i == values.length - 1) {
                x = values[i] + values[i - 1];
                result[i] = x / 2;
            } else {
                x = values[i] + values[i - 1] + values[i + 1];
                result[i] = x / 3;
            }
        }
        return result;
    }
```