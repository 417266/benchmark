import 'dart:math';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

const loremIpsum =
    'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vel augue quis sapien congue faucibus ac sed enim. Phasellus fermentum interdum arcu, id consectetur dui laoreet vitae.';

void main() {
  runApp(const BenchmarkApp());
}

class BenchmarkApp extends StatelessWidget {
  const BenchmarkApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        scaffoldBackgroundColor: Colors.white,
        colorScheme: const ColorScheme.light(),
      ),
      home: AnnotatedRegion<SystemUiOverlayStyle>(
        value: SystemUiOverlayStyle.dark,
        child: Scaffold(
          body: ListView.builder(
            itemCount: 1000,
            itemBuilder: (context, index) => ListItem(index: index),
          ),
        ),
      ),
    );
  }
}

class ListItem extends StatefulWidget {
  const ListItem({super.key, required this.index});

  final int index;

  @override
  State<ListItem> createState() => _ListItemState();
}

class _ListItemState extends State<ListItem>
    with SingleTickerProviderStateMixin {
  late final AnimationController _controller;
  late final String _text;

  @override
  void initState() {
    super.initState();
    _text = loremIpsum.substring(0, Random().nextInt(loremIpsum.length) + 1);
    _controller = AnimationController(
      vsync: this,
      duration: const Duration(milliseconds: 1000),
    )..repeat();
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(16),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.center,
        spacing: 16,
        children: [
          CachedNetworkImage(
            imageUrl:
                'https://placehold.co/256x256.png?text=${widget.index + 1}',
            width: 48,
            height: 48,
          ),
          Expanded(child: Text(_text)),
          AnimatedBuilder(
            animation: _controller,
            builder: (context, child) {
              return Transform.rotate(
                angle: _controller.value * 2 * pi,
                child: child,
              );
            },
            child: SizedBox(
              width: 24,
              height: 24,
              child: ColoredBox(color: Colors.black),
            ),
          ),
        ],
      ),
    );
  }
}
