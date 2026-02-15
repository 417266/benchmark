import { FlatList, StatusBar, StyleSheet, Text, View } from "react-native";
import {
  SafeAreaProvider,
  useSafeAreaInsets,
} from "react-native-safe-area-context";
import { Image } from "expo-image";
import Animated, {
  useAnimatedStyle,
  useSharedValue,
  withRepeat,
  withTiming,
  Easing,
} from "react-native-reanimated";
import { useEffect } from "react";

const LOREM_IPSUM =
  "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vel augue quis sapien congue faucibus ac sed enim. Phasellus fermentum interdum arcu, id consectetur dui laoreet vitae.";

const ITEM_COUNT = 1000;

const DATA = Array.from({ length: ITEM_COUNT }, (_, i) => i);

function ListItem({ index }) {
  const text = LOREM_IPSUM.slice(
    0,
    Math.floor(Math.random() * LOREM_IPSUM.length) + 1,
  );

  const rotation = useSharedValue(0);

  useEffect(() => {
    rotation.value = withRepeat(
      withTiming(360, { duration: 1000, easing: Easing.linear }),
      -1,
      false,
    );
  }, []);

  const animatedStyle = useAnimatedStyle(() => ({
    transform: [{ rotateZ: `${rotation.value}deg` }],
  }));

  return (
    <View style={styles.row}>
      <Image
        source={{ uri: `https://placehold.co/256x256.png?text=${index + 1}` }}
        style={styles.image}
      />
      <Text style={styles.text}>{text}</Text>
      <Animated.View style={[styles.box, animatedStyle]} />
    </View>
  );
}

const renderItem = ({ item }) => <ListItem index={item} />;

const keyExtractor = (item) => item.toString();

function BenchmarkList() {
  const insets = useSafeAreaInsets();
  return (
    <FlatList
      data={DATA}
      renderItem={renderItem}
      keyExtractor={keyExtractor}
      contentContainerStyle={{
        paddingTop: insets.top,
        paddingBottom: insets.bottom,
      }}
    />
  );
}

export default function App() {
  return (
    <SafeAreaProvider style={styles.container}>
      <StatusBar barStyle="dark-content" />
      <BenchmarkList />
    </SafeAreaProvider>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
  },
  row: {
    flexDirection: "row",
    alignItems: "center",
    padding: 16,
    gap: 16,
  },
  image: {
    width: 48,
    height: 48,
  },
  text: {
    flex: 1,
  },
  box: {
    width: 24,
    height: 24,
    backgroundColor: "#000",
  },
});
